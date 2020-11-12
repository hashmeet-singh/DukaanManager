package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.Staff;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaff(Long staffId) {
        return staffRepository
                .findById(staffId)
                .orElseThrow(() -> new RecordNotFoundException("Invalid Staff Id: " + staffId));
    }

    @Override
    public boolean validateStaff(Staff staff) {
        if (!staff.getEmail().isEmpty() && !staffRepository.findByEmail(staff.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + staff.getEmail() + " is already registered.");
        }

        if (!staffRepository.findByPhoneNo(staff.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + staff.getPhoneNo() + " is already registered.");
        }
        return true;
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }
}
