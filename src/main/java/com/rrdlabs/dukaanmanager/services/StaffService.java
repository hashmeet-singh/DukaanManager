package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getAllStaff();

    Staff getStaff(Long staffId);

    void validateStaff(Staff staff);

    Staff createStaff(Staff staff);

    Staff updateStaff(Staff staff);

    void deleteStaff(Long staffId);
}
