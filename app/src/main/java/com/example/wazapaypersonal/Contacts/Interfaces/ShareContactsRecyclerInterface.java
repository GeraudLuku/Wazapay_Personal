package com.example.wazapaypersonal.Contacts.Interfaces;

import com.example.wazapaypersonal.Contacts.Models.Contact;

import java.util.ArrayList;

public interface ShareContactsRecyclerInterface {
    void onContactSelected(Contact contact, boolean isChecked);
    void getContacts(ArrayList<Contact> contactArrayList);
}
