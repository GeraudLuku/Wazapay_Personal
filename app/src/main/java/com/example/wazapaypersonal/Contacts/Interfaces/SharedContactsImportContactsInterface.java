package com.example.wazapaypersonal.Contacts.Interfaces;

import com.example.wazapaypersonal.Contacts.Models.Contact;

public interface SharedContactsImportContactsInterface {
    //    void onContactSelected(Contact contact, boolean isChecked);
    void onContactSelected(Contact contact, int position, boolean isChecked);
}
