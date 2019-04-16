package com.code.controller;

import com.code.model.Customer;
import com.code.service.CustomerService;
import com.code.service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends javax.servlet.http.HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null){
        action = "";
    }
    switch (action){
        case "create":
            createCustomer(request,response);
            break;
        case "edit":
            updateCustomer(request,response);
            break;
        case "delete":
            break;
            default:
                break;
    }
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            customer.setName(name);
            customer.setEmail(email);
            customer.setAddress(address);
            this.customerService.update(id,customer);
            request.setAttribute("customer",customer);
            request.setAttribute("message","customer information was updated");
            dispatcher = request.getRequestDispatcher("customer/edit.jsp");
            try {
                dispatcher.forward(request,response);
            }catch (ServletException | IOException e){
                e.printStackTrace();
            }
        }
    }

    private void lisCustomers(HttpServletRequest request, HttpServletResponse response) {
        List<Customer>customers = this.customerService.findAll();
        request.setAttribute("customers",customers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
        try {
            dispatcher.forward(request,response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                break;
            case "view":
                break;
                default:
                    lisCustomers(request,response);
                    break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = this.customerService.findById(id);
        RequestDispatcher dispatcher;
        if (customer == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("customer",customer);
            dispatcher = request.getRequestDispatcher("customer/edit.jsp");
        }
        try {
            dispatcher.forward(request,response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int id = (int)(Math.random() * 10000);

        Customer customer = new Customer(id, name, email, address);
        this.customerService.save(customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        request.setAttribute("message", "New customer was created");
        try {
            dispatcher.forward(request,response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }
}
