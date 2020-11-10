package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();

    Staff getStaff(int staffId);

    boolean validateStaff(Staff staff);

    Staff createStaff(Staff staff);
}
