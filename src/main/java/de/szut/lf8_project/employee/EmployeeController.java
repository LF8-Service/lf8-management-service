package de.szut.lf8_project.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.szut.lf8_project.projekt.ProjektController;
import de.szut.lf8_project.projekt.ProjektMapper;
import de.szut.lf8_project.projekt.ProjektService;
import de.szut.lf8_project.projekt.entity.ProjektEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    EmployeeService employeeService = new EmployeeService();
    public EmployeeEntity getEmployeePerId(long id){
        String newUrl = "https://employee.szut.dev/employees/";
        //String oldUrl = "https://employeeold.szut.dev/employees/";
        String json = employeeService.makeGetRequest(newUrl+id);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            EmployeeEntity employeeEntity = objectMapper.readValue(json, EmployeeEntity.class);
            System.out.println(employeeEntity.getLastName());
            return employeeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isEmployeesIdExists(long id){
         String newUrl = "https://employee.szut.dev/employees/";
       // String oldUrl = "https://employeeold.szut.dev/employees/";
        return !employeeService.makeGetRequest(newUrl+id).equals("404");
    }
    // Ich mache nur eine Anfrage zu Employee Service. Ich bekomme die Liste von allen Employees und checke jede Employee Id in Projekt in diese Liste.
    public boolean checkEmployeesIdInOneProjekt(ProjektEntity projekt) {
        List<EmployeeEntity> employees = getAllEmployees();
        boolean isResponsableEmployeeExist = checkEmployeeIdInList(projekt.getResponsableEmployeeId(), employees);
        if (!isResponsableEmployeeExist) {
            return false;
        }
        boolean isCustomerEmployeeExist = checkEmployeeIdInList(projekt.getCustomerEmployeeId(), employees);
        if (!isCustomerEmployeeExist) {
            return false;
        }
        for (Long employeesId : projekt.getEmployees()) {
            boolean isIdExist = checkEmployeeIdInList(employeesId, employees);
            if (!isIdExist) {
                return false;
            }
        }
        return true;
    }

    // Ich mache nur eine Anfrage zu Employee Service. Ich bekomme die Liste von allen Employees und checke jede Employee Id in Projekt in diese Liste.
    public boolean checkEmployeeIdInList(long id, List<EmployeeEntity> list) {
        for (EmployeeEntity employeeEntity : list) {
            if (employeeEntity.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public List<EmployeeEntity> getAllEmployees(){
         String newUrl = "https://employee.szut.dev/employees/";
       // String oldUrl = "https://employeeold.szut.dev/employees/";
       String json = employeeService.makeGetRequest(newUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<EmployeeEntity> employeeEntityList = objectMapper.readValue(json,new TypeReference<List<EmployeeEntity>>() {});
            return employeeEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
