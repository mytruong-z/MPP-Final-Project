package model;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
    private List<CheckoutRecordEntry> checkOutEntries;

    public CheckoutRecord() {
        checkOutEntries = new ArrayList<>();
    }


    public void addCheckOutRecordEntry(CheckoutRecordEntry checkOutRecordEntry) {
        checkOutEntries.add(checkOutRecordEntry);
        checkOutRecordEntry.setCheckOutRecord(this);
    }

    public List<CheckoutRecordEntry> getCheckOutRecordEntries(){
        return checkOutEntries;
    }
}
