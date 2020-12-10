package com.rrdlabs.dukaanmanager.services.implementations;

import com.rrdlabs.dukaanmanager.entities.Staff;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.StaffRepository;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void validateStaff(Staff staff) {
        if (!staff.getEmail().isEmpty() && !staffRepository.findByEmail(staff.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + staff.getEmail() + " is already registered.");
        }

        if (!staffRepository.findByPhoneNo(staff.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + staff.getPhoneNo() + " is already registered.");
        }
    }

    @Override
    @Transactional
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }
}
