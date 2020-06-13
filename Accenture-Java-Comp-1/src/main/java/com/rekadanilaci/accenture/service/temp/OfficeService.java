package com.rekadanilaci.accenture.service.temp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfficeService {
    /*private EmployeeRepository employeeRepository;
    private ReservationRepository reservationRepository;
    private Office office;

    @Autowired
    public OfficeService(EmployeeRepository employeeRepository, ReservationRepository reservationRepository) {
        this.employeeRepository = employeeRepository;
        this.reservationRepository = reservationRepository;
        this.office = Office.getInstance();
    }

    public void createReservation(ReservationDto reservationDto) throws Exception {
        Long employeeId = reservationDto.getPerson();
        Employee employee = employeeRepository.getOne(employeeId);
        LocalDate day = reservationDto.getDay();
       /* switch (office.registerReservation(employeeId, day)) {
            case INVALID_ID:
                //throw new Exception("Invalid employee ID.");
            case INVALID_DAY:
                throw new Exception("Invalid date.");
            case ALREADY_CREATED:
                throw new Exception("Reservation already exists.");
            case CREATED:
                saveReservationToDatabase(employee, day);
        }
        office.registerReservation(employeeId, day);
    }


    public String requestStatus(Long employeeId) {
        String status = office.reportStatus(employeeId, LocalDate.now());
        return status;
    }


    public boolean checkEntryRequest(Long employeeId) {
        boolean canEntry = office.requestEntry(employeeId);
        return canEntry;
    }

    public void exitOffice(Long employeeId) {
        office.exit(employeeId);
    }

    // =========== GENERAL METHODS ===========

    private void saveReservationToDatabase(Employee employee, LocalDate day) {
        Reservation newReservation = new Reservation(employee, day);
        reservationRepository.save(newReservation);
    }

    /*private void saveEmployeeToDatabase(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto);
        employeeRepository.save(employee);
    }

    public void fillEmployees() {
        for (int i = 0; i < 200; i++) {
            Employee employee = new Employee();
            employee.setName("Employee" + i);
            office.addEmployee(employee);
            employeeRepository.save(employee);
        }
        int size = office.getStaff().size();
    }
    */

}


