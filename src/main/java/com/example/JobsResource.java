package com.example;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Add your first API methods in this class, or you may create another class. In that case, please
 * update your web.xml accordingly.
 **/
@Api(
        name = "jobs",
        version = "v1"
)
public class JobsResource {
    private List<Employee> employees = new ArrayList<>();

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, name = "getJobs", path = "jobs")
    public List<Employee> getEmployees() {
        return employees;
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.POST, name = "createJob", path = "jobs")
    public Employee createEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }
}
