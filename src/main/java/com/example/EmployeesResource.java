package com.example;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;

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
public class EmployeesResource {
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, name = "getJobs", path = "employees")
    public List<Employee> getEmployees() {
        Query q = new Query("employee");
        Iterable<Entity> entities = datastore.prepare(q).asIterable();

        List<Employee> employees = new ArrayList<>();

        for (Entity entity : entities) {
            Employee e = new Employee();
            e.setJob((String) entity.getProperty("job"));
            e.setAge((Long) entity.getProperty("age"));
            e.setName((String) entity.getProperty("name"));
            employees.add(e);
        }

        return employees;
    }

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.POST, name = "createJob", path = "employees")
    public Employee createEmployee(Employee employee) {
        Entity entity = new Entity("employee");
        entity.setProperty("name", employee.getName());
        entity.setProperty("age", employee.getAge());
        entity.setProperty("job", employee.getJob());

        datastore.put(entity);
        return employee;
    }
}
