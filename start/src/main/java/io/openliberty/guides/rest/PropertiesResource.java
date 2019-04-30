// tag::comment[]
/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
 // end::comment[]
package io.openliberty.guides.rest;

import com.google.gson.Gson;
import java.util.Properties;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonArray;
import javax.json.Json;
import javax.json.JsonNumber;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// tag::class[]
@Path("properties")
public class PropertiesResource {
// end::class[]

    // tag::getProperties[]
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProperties() {
        
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "admin");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
      
      List<Cliente> clientes = new ArrayList<Cliente>();
      try {
        Statement stmt = c.createStatement();

        String sql = "SELECT * FROM tb_clientes";
        ResultSet rs = stmt.executeQuery(sql);
        //STEP 5: Extract data from result set
        while(rs.next()){
            Cliente cliente = new Cliente();
           //Retrieve by column name
           cliente.setNome(rs.getString("nm_cliente"));
           cliente.setCpf(rs.getString("cpf_cliente"));
           cliente.setEndereco(rs.getString("end_cliente"));
           cliente.setCidade(rs.getString("cid_cliente"));
           cliente.setEstado(rs.getString("est_cliente"));
           clientes.add(cliente);
        }
        rs.close();          
      } catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }


        JsonObjectBuilder builder = Json.createObjectBuilder();
        

        String json = new Gson().toJson(clientes);
       return json;
    }
    // end::getProperties[]
}
