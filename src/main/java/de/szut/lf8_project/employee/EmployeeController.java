package de.szut.lf8_project.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class EmployeeController {
    EmployeeService employeeService = new EmployeeService();
    public EmployeeEntity getEmployeePerId(long id){
        String json = employeeService.makeGetRequest("https://employee.szut.dev/employees/"+id);
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
        return !employeeService.makeGetRequest("https://employee.szut.dev/employees/"+id).equals("404");
    }
    public boolean checkEmployeeIdInList(long id, List<EmployeeEntity> list) {
        for (EmployeeEntity employeeEntity : list) {
            if (employeeEntity.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public List<EmployeeEntity> getAllEmployees(){
       String json = employeeService.makeGetRequest("https://employee.szut.dev/employees");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<EmployeeEntity> employeeEntityList = objectMapper.readValue(json,new TypeReference<List<EmployeeEntity>>() {});
            return employeeEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeController employeeController = new EmployeeController();
      System.out.println(employeeController.isEmployeesIdExists(2));
    }

}
