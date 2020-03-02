package com.example.wazapaypersonal.Agent_Listing.Feature;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wazapaypersonal.Agent_Listing.Model.Agent;
import com.example.wazapaypersonal.Agent_Listing.ViewModel.ViewModelClass;
import com.example.wazapaypersonal.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapViewFragment extends Fragment implements OnMapReadyCallback {
    private static final int REQUEST_PERMISSION_CODE = 202;

    private GoogleMap mMap;

    private Location mUserLocation;

    private ArrayList<Agent> mAgentList = new ArrayList<>();

    private ViewModelClass mViewModelClass;

    public MapViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModelClass = new ViewModelProvider(getActivity()).get(ViewModelClass.class);
        //observe selected contacts object
        mViewModelClass.getSearchKey().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //get search filter
                //search in list of agents if exist
                for (Agent agent : mAgentList) {
                    if (agent.getName().toLowerCase().contains(s)) {
                        viewAgent(new LatLng(agent.getLat(), agent.getLng()));
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);

        //create dummy favourite contacts
        mAgentList.add(new Agent("Fon Ndikum", "1,000,000 CFA", 4.155036, 9.290329));
        mAgentList.add(new Agent("Luku Geraud", "900,000 CFA", 4.154405, 9.290087));
        mAgentList.add(new Agent("Frankie", "20,000 CFA", 4.154068, 9.290661));
        mAgentList.add(new Agent("Godlove Fonzeyuy", "40,000,000 CFA", 4.154448, 9.291541));
        mAgentList.add(new Agent("Louraine Pink", "1,000 CFA", 4.154667, 9.290774));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (checkPermission()) {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            centerMaptoMyLocation();
            loadAgentsOnMap();
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(getActivity(), new String[]
                {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, REQUEST_PERMISSION_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {

            case REQUEST_PERMISSION_CODE:

                if (grantResults.length > 0) {

                    boolean finelocation = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean coarselocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (finelocation && coarselocation) {
                        if (checkPermission()) {
                            buildGoogleApiClient();
                            mMap.setMyLocationEnabled(true);
                            mMap.getUiSettings().setMyLocationButtonEnabled(true);
                            loadAgentsOnMap();
                        }
                        Toast.makeText(getContext(), "Location Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Location And Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }
                break;
        }
    }

    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED;

    }

    private GoogleApiClient mGoogleApiClient;

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(mGoogleApiClientConnectionCallbacks)
                .addOnConnectionFailedListener(mGoogleApiClientConnectionFailedListener)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private GoogleApiClient.ConnectionCallbacks mGoogleApiClientConnectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            Toast.makeText(getContext(), "Connected - google maps api", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onConnectionSuspended(int i) {
            Toast.makeText(getContext(), "Suspended - google maps api", Toast.LENGTH_SHORT).show();
        }
    };

    private GoogleApiClient.OnConnectionFailedListener mGoogleApiClientConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(getContext(), "Failed - google maps api", Toast.LENGTH_SHORT).show();
        }
    };

    private void centerMaptoMyLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        // Location wasn't found, check the next most accurate place for the current location
        if (location == null) {
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            // Finds a provider that matches the criteria
            String provider = locationManager.getBestProvider(criteria, true);
            // Use the provider to get the last known location
            location = locationManager.getLastKnownLocation(provider);
        }

        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }


    private void viewAgent(LatLng latLng) {
        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

        for (Marker marker : mMarkers) {
            LatLng latLng1 = marker.getPosition();
            if (latLng1.equals(latLng))
                marker.showInfoWindow();

        }
    }

    //store all agent markers in array list
    private ArrayList<Marker> mMarkers = new ArrayList<>();

    private void loadAgentsOnMap() {
        for (Agent agent : mAgentList) {
            //create marker
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(agent.getLat(), agent.getLng())) //setting position
                    .draggable(false) //Making the marker non draggable
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.agent_map_person)) //icon image here i am going to put the users bitmojii
                    .title(agent.getName())); //Adding my name as title
            mMarkers.add(marker);
        }
    }


    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
