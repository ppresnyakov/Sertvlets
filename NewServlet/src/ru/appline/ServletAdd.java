package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.print.attribute.standard.PrinterURI;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.server.ExportException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")

public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson =new GsonBuilder().setPrettyPrinting().create();
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
 //       Double salary = Double.parseDouble(request.getParameter("salary"));
//
 //       User user = new User(name,surname,salary);
//        model.add(user, counter.getAndDecrement());
//
//        pw.println("<html" +
//                "<h3>Пользователь " + name + " " + surname + " " + "с зарплатой= " + salary + " успещно создан! </h3" +
//                "<a href=\"addUser.html\"> Создать нового пользователя</a><br/>" +
//                "<a href=\"index.jsp\"> Домой</a><br/>" +
//                "</html>");
//   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
       StringBuffer jb = new StringBuffer();
       String line;
       try {
           BufferedReader reader  = request.getReader();
           while ((line = reader.readLine()) != null){
               jb.append(line);
           }
       } catch (ExportException e){
           System.out.println("Error!");
       }

       JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
       request.setCharacterEncoding("UTF-8");

       String  name  = jobj.get("name").getAsString();
       String  surname  = jobj.get("surname").getAsString();
       Double  salary  = jobj.get("salary").getAsDouble();


       User user = new User(name, surname, salary);
       model.add(user, counter.getAndDecrement());

       response.setContentType("application/json;charset=UTF-8");
       PrintWriter pw = response.getWriter();

       pw.print(gson.toJson(model.getFromList()));
   }


}
