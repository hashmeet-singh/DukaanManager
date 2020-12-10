package com.rrdlabs.dukaanmanager.controllers;

import com.rrdlabs.dukaanmanager.entities.Customer;
import com.rrdlabs.dukaanmanager.entities.Order;
import com.rrdlabs.dukaanmanager.entities.Staff;
import com.rrdlabs.dukaanmanager.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staffs")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAllStaffs() {
        return staffService.getAllStaff();
    }

    @GetMapping("/{staff_id}")
    public Staff getStaffById(@PathVariable(name = "staff_id") Long id) {
        return staffService.getStaff(id);
    }

    @PostMapping
    public ResponseEntity<Staff> createStaff(@Valid @RequestBody Staff staff) {
        staffService.validateStaff(staff);
        Staff createdStaff = staffService.createStaff(staff);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @PutMapping("/{staff_id}")
    public Staff updateStaff(@PathVariable(name = "staff_id") Long id, @Valid @RequestBody Staff staff) {
        staffService.getStaff(id);
        staff.setId(id);
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{staff_id}")
    public void deleteStaff(@PathVariable(name = "staff_id") Long id) {
        staffService.deleteStaff(id);
    }

    // TODO: 10-12-2020 Implement logic for API
    @GetMapping("/{staff_id}/orders")
    public List<Order> getOrdersSoldByStaff(@PathVariable(name = "staff_id") Long id) {
        return null;
    }
}
