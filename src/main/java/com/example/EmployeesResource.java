package com.example;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.oauth.OAuthRequestException;

import java.util.ArrayList;
import java.util.List;

/**
 * Add your first API methods in this class, or you may create another class. In that case, please
 * update your web.xml accordingly.
 **/
@Api(
        name = "jobs",
        version = "v1",
        scopes = {"https://www.googleapis.com/auth/userinfo.email"},
        clientIds = {Constants.WEB_CLIENT_ID}
)
public class EmployeesResource {
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, name = "getJobs", path = "employees")
    public List<Employee> getEmployees(User user) throws OAuthRequestException {
        if (user == null) {
            throw new OAuthRequestException("No valid credentials supplied");
        }

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
    public Employee createEmployee(Employee employee, User user) throws OAuthRequestException {
        if (user == null) {
            throw new OAuthRequestException("No valid credentials supplied");
        }

        Entity entity = new Entity("employee");
        entity.setProperty("name", employee.getName());
        entity.setProperty("age", employee.getAge());
        entity.setProperty("job", employee.getJob());

        datastore.put(entity);
        return employee;
    }
}
