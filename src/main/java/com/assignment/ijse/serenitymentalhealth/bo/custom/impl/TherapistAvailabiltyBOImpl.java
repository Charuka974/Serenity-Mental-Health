package com.assignment.ijse.serenitymentalhealth.bo.custom.impl;

import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistAvailabiltyBO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapistAvailabiltyDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapistAvailabiltyDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dao.custom.impl.TherapistDAOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TherapistAvailabiltyBOImpl implements TherapistAvailabiltyBO {


    private TherapistAvailabiltyDAO availabilityDAO = new TherapistAvailabiltyDAOImpl();
    private TherapistDAO therapistDAO = new TherapistDAOImpl();

    @Override
    public boolean saveAvailability(TherapistAvailabilityDto dto) {
        Optional<Therapist> therapistOpt = therapistDAO.findById(dto.getTherapistId());
        if (therapistOpt.isEmpty()) {
            return false;
        }
        Therapist therapist = therapistOpt.get();
        TherapistAvailability availability = new TherapistAvailability(
                dto.getAvailabilityId(),
                therapist,
                dto.getAvailableDate(),
                dto.getAvailableTime(),
                dto.isAvailable()
        );

        return availabilityDAO.save(availability);
    }

    @Override
    public boolean updateAvailability(TherapistAvailabilityDto dto) {
        Optional<Therapist> therapistOpt = therapistDAO.findById(dto.getTherapistId());
        if (therapistOpt.isEmpty()) {
            return false;
        }
        Therapist therapist = therapistOpt.get();
        TherapistAvailability availability = new TherapistAvailability(
                dto.getAvailabilityId(),
                therapist,
                dto.getAvailableDate(),
                dto.getAvailableTime(),
                dto.isAvailable()
        );

        return availabilityDAO.update(availability);
    }

    @Override
    public boolean deleteAvailability(String pk) {
        return availabilityDAO.delete(pk);
    }

    @Override
    public ArrayList<TherapistAvailabilityDto> getAllAvailabilities() {
        List<TherapistAvailability> availabilities = availabilityDAO.getAll();
        ArrayList<TherapistAvailabilityDto> dtos = new ArrayList<>();

        for (TherapistAvailability a : availabilities) {
            dtos.add(new TherapistAvailabilityDto(
                    a.getAvailability_id(),
                    a.getTherapist().getTherapist_id(),
                    a.getAvailable_date(),
                    a.getAvailable_time(),
                    a.is_available()
            ));
        }
        return dtos;
    }

    @Override
    public ArrayList<TherapistAvailabilityDto> findByTherapist(String name) {
        List<TherapistAvailability> availabilities = availabilityDAO.findByTherapist(name);
        ArrayList<TherapistAvailabilityDto> dtos = new ArrayList<>();

        for (TherapistAvailability a : availabilities) {
            dtos.add(new TherapistAvailabilityDto(
                    a.getAvailability_id(),
                    a.getTherapist().getTherapist_id(),
                    a.getAvailable_date(),
                    a.getAvailable_time(),
                    a.is_available()
            ));
        }
        return dtos;
    }

    @Override
    public String getNextPK() {
        Optional<String> lastPkOpt = availabilityDAO.getLastPK();
        if (lastPkOpt.isEmpty()) {
            return "TA001";
        }

        String lastPk = lastPkOpt.get();
        int num = Integer.parseInt(lastPk.replace("TA", ""));
        return String.format("TA%03d", num + 1);
    }

}
