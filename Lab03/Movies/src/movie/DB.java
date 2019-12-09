package movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import movie.Movie;
import movie.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DB {



//Database connection properties -----------------------------------------------------------------------------------------------------------------------			

    public static Connection connectionWithDatabase() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String db_url = "jdbc:mysql://remotemysql.com:3306/TqVcWsXfaw";
            String user = "TqVcWsXfaw";
            String password = "FRXjWbr1as";

            conn = DriverManager.getConnection(db_url, user, password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }


//Navigation Users * -----------------------------------------------------------------------------------------------------------------------	

//Navigation Users.New_user	--------------------------------------------------------------------------------------------------------------------

    protected static String makingUserID() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    public void addUser(){

        String id_korisnika = makingUserID();

        String sql = "INSERT INTO Korisnik (ime,id_korisnika,prezime,jmbg,adresa,phone,email) VALUES('"+Front.firstnameTF.getText()+"','"+id_korisnika+"','"+Front.lastnameTF.getText()+"','"+Front.ssnTF.getText()+"','"+Front.adressTF.getText()+"','"+Front.phone_numberTF.getText()+"','"+Front.emailTF.getText()+"')";


        if( Front.firstnameTF.getText().equals("") || Front.lastnameTF.getText().equals("") || Front.ssnTF.getText().equals("") || Front.adressTF.getText().equals("") || Front.phone_numberTF.getText().equals("") ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill in all the fields with * ");

            alert.showAndWait();
        }
        else{

            try{
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {
                String sqlUzimiID = "SELECT * FROM Korisnik WHERE jmbg='" + Front.ssnTF.getText() + "';";

                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlUzimiID);

                String idKorisnika = "";
                String imeiprezime = "";
                if(rs.next()){
                    idKorisnika = rs.getString("id_korisnika");
                    imeiprezime = rs.getString("ime") + " " + rs.getString("prezime");
                }

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully added a new user " + imeiprezime +".\nHis ID is: "  +idKorisnika);
                alert.showAndWait();

                Front.firstnameTF.clear();
                Front.lastnameTF.clear();
                Front.ssnTF.clear();
                Front.adressTF.clear();
                Front.phone_numberTF.clear();
                Front.emailTF.clear();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }


    //Navigation User.Edit_user --------------------------------------------------------------------------------------------------------------------	
    public void allowUserEdit(){

        String sql ="SELECT * FROM Korisnik WHERE id_korisnika='"+Front.idTF2.getText()+"';";

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()==true){
                Front.idL2.setDisable(true);
                Front.idTF2.setDisable(true);
                Front.searchButton2.setDisable(true);

                Front.firstnameTF2.setDisable(false);
                Front.lastnameTF2.setDisable(false);
                Front.ssnTF2.setDisable(false);
                Front.adressTF2.setDisable(false);
                Front.phone_numberTF2.setDisable(false);
                Front.emailTF2.setDisable(false);
                Front.firstnameL2.setDisable(false);
                Front.lastnameL2.setDisable(false);
                Front.ssnL2.setDisable(false);
                Front.adressL2.setDisable(false);
                Front.phone_numberL2.setDisable(false);
                Front.emailL2.setDisable(false);
                Front.editButton2.setDisable(false);
                Front.changeUserButton2.setDisable(false);

                Front.firstnameTF2.setText(rs.getString("ime"));
                Front.lastnameTF2.setText(rs.getString("prezime"));
                Front.ssnTF2.setText(rs.getString("jmbg"));
                Front.adressTF2.setText(rs.getString("adresa"));
                Front.phone_numberTF2.setText(rs.getString("phone"));
                Front.emailTF2.setText(rs.getString("email"));


                Front.firstnameTF2.setStyle(null);
                Front.lastnameTF2.setStyle(null);
                Front.ssnTF2.setStyle(null);
                Front.adressTF2.setStyle(null);
                Front.phone_numberTF2.setStyle(null);
                Front.emailTF2.setStyle(null);
                Front.editButton2.setStyle(null);
            }
            else{
                Front.firstnameTF2.clear();
                Front.lastnameTF2.clear();
                Front.ssnTF2.clear();
                Front.adressTF2.clear();
                Front.phone_numberTF2.clear();
                Front.emailTF2.clear();

                Front.firstnameTF2.setDisable(true);
                Front.lastnameTF2.setDisable(true);
                Front.ssnTF2.setDisable(true);
                Front.adressTF2.setDisable(true);
                Front.phone_numberTF2.setDisable(true);
                Front.emailTF2.setDisable(true);
                Front.firstnameL2.setDisable(true);
                Front.lastnameL2.setDisable(true);
                Front.ssnL2.setDisable(true);
                Front.adressL2.setDisable(true);
                Front.phone_numberL2.setDisable(true);
                Front.emailL2.setDisable(true);
                Front.editButton2.setDisable(true);


                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You must insert valid user ID! ");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            //This is if user put characters in ID
            Front.firstnameTF2.clear();
            Front.lastnameTF2.clear();
            Front.ssnTF2.clear();
            Front.adressTF2.clear();
            Front.phone_numberTF2.clear();
            Front.emailTF2.clear();

            Front.firstnameTF2.setDisable(true);
            Front.lastnameTF2.setDisable(true);
            Front.ssnTF2.setDisable(true);
            Front.adressTF2.setDisable(true);
            Front.phone_numberTF2.setDisable(true);
            Front.emailTF2.setDisable(true);
            Front.firstnameL2.setDisable(true);
            Front.lastnameL2.setDisable(true);
            Front.ssnL2.setDisable(true);
            Front.adressL2.setDisable(true);
            Front.phone_numberL2.setDisable(true);
            Front.emailL2.setDisable(true);
            Front.editButton2.setDisable(true);


            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must insert valid user ID! ");

            alert.showAndWait();
        }

    }

    public void change_userEdit(){

        Front.idL2.setDisable(false);
        Front.idTF2.setDisable(false);
        Front.searchButton2.setDisable(false);

        Front.idTF2.clear();
        Front.firstnameTF2.clear();
        Front.lastnameTF2.clear();
        Front.ssnTF2.clear();
        Front.adressTF2.clear();
        Front.phone_numberTF2.clear();
        Front.emailTF2.clear();

        Front.firstnameTF2.setDisable(true);
        Front.lastnameTF2.setDisable(true);
        Front.ssnTF2.setDisable(true);
        Front.adressTF2.setDisable(true);
        Front.phone_numberTF2.setDisable(true);
        Front.emailTF2.setDisable(true);
        Front.firstnameL2.setDisable(true);
        Front.lastnameL2.setDisable(true);
        Front.ssnL2.setDisable(true);
        Front.adressL2.setDisable(true);
        Front.phone_numberL2.setDisable(true);
        Front.emailL2.setDisable(true);
        Front.editButton2.setDisable(true);
        Front.changeUserButton2.setDisable(true);

    }

    public void editUser(){

        String sql = "UPDATE Korisnik SET ime='"+Front.firstnameTF2.getText()+"',prezime='"+Front.lastnameTF2.getText()+"',jmbg="+Front.ssnTF2.getText()+",adresa='"+Front.adressTF2.getText()+"',phone="+Front.phone_numberTF2.getText()+",email='"+Front.emailTF2.getText()+"' WHERE id_korisnika='"+Front.idTF2.getText()+"';";

        if( Front.firstnameTF2.getText().equals("") || Front.lastnameTF2.getText().equals("") || Front.ssnTF2.getText().equals("") || Front.adressTF2.getText().equals("") || Front.phone_numberTF2.equals("") ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must fill all fields with * ");

            alert.showAndWait();
        }
        else{


            try {
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                stmt.executeUpdate(sql);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully edited the selected user.");
            alert.showAndWait();

            Front.idL2.setDisable(false);
            Front.idTF2.setDisable(false);
            Front.searchButton2.setDisable(false);

            Front.idTF2.clear();
            Front.firstnameTF2.clear();
            Front.lastnameTF2.clear();
            Front.ssnTF2.clear();
            Front.adressTF2.clear();
            Front.phone_numberTF2.clear();
            Front.emailTF2.clear();

            Front.firstnameTF2.setDisable(true);
            Front.lastnameTF2.setDisable(true);
            Front.ssnTF2.setDisable(true);
            Front.adressTF2.setDisable(true);
            Front.phone_numberTF2.setDisable(true);
            Front.emailTF2.setDisable(true);
            Front.firstnameL2.setDisable(true);
            Front.lastnameL2.setDisable(true);
            Front.ssnL2.setDisable(true);
            Front.adressL2.setDisable(true);
            Front.phone_numberL2.setDisable(true);
            Front.emailL2.setDisable(true);
            Front.editButton2.setDisable(true);
            Front.changeUserButton2.setDisable(true);

        }

    }


    //Navigation Users.Show_user --------------------------------------------------------------------------------------------------------------------	
    public void showUser(){

        String sql ="SELECT * FROM Korisnik WHERE id_korisnika='"+Front.idTF3.getText()+"';";

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                Front.firstnameTF3.setDisable(false);
                Front.lastnameTF3.setDisable(false);
                Front.ssnTF3.setDisable(false);
                Front.adressTF3.setDisable(false);
                Front.phone_numberTF3.setDisable(false);
                Front.emailTF3.setDisable(false);
                Front.firstnameL3.setDisable(false);
                Front.lastnameL3.setDisable(false);
                Front.ssnL3.setDisable(false);
                Front.adressL3.setDisable(false);
                Front.phone_numberL3.setDisable(false);
                Front.emailL3.setDisable(false);


                Front.firstnameTF3.setText(rs.getString("ime"));
                Front.lastnameTF3.setText(rs.getString("prezime"));
                Front.ssnTF3.setText(rs.getString("jmbg"));
                Front.adressTF3.setText(rs.getString("adresa"));
                Front.phone_numberTF3.setText(rs.getString("phone"));
                Front.emailTF3.setText(rs.getString("email"));
            }else{

                Front.firstnameTF3.clear();
                Front.lastnameTF3.clear();
                Front.ssnTF3.clear();
                Front.adressTF3.clear();
                Front.phone_numberTF3.clear();
                Front.emailTF3.clear();

                Front.firstnameTF3.setDisable(true);
                Front.lastnameTF3.setDisable(true);
                Front.ssnTF3.setDisable(true);
                Front.adressTF3.setDisable(true);
                Front.phone_numberTF3.setDisable(true);
                Front.emailTF3.setDisable(true);
                Front.firstnameL3.setDisable(true);
                Front.lastnameL3.setDisable(true);
                Front.ssnL3.setDisable(true);
                Front.adressL3.setDisable(true);
                Front.phone_numberL3.setDisable(true);
                Front.emailL3.setDisable(true);



                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You must enter valid user ID! ");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    //Navigation Users.Delete_user --------------------------------------------------------------------------------------------------------------------	
    public void allow_deleting_user(){

        String sql ="SELECT * FROM Korisnik WHERE id_korisnika='"+Front.idTF4.getText()+"';";

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            if(rs.next()==true){
                Front.idL4.setDisable(true);
                Front.idTF4.setDisable(true);
                Front.searchButton4.setDisable(true);

                Front.firstnameTF4.setDisable(false);
                Front.lastnameTF4.setDisable(false);
                Front.ssnTF4.setDisable(false);
                Front.adressTF4.setDisable(false);
                Front.phone_numberTF4.setDisable(false);
                Front.emailTF4.setDisable(false);
                Front.firstnameL4.setDisable(false);
                Front.lastnameL4.setDisable(false);
                Front.ssnL4.setDisable(false);
                Front.adressL4.setDisable(false);
                Front.phone_numberL4.setDisable(false);
                Front.emailL4.setDisable(false);
                Front.deleteButton4.setDisable(false);
                Front.changeUserButton4.setDisable(false);


                Front.firstnameTF4.setText(rs.getString("ime"));
                Front.lastnameTF4.setText(rs.getString("prezime"));
                Front.ssnTF4.setText(rs.getString("jmbg"));
                Front.adressTF4.setText(rs.getString("adresa"));
                Front.phone_numberTF4.setText(rs.getString("phone"));
                Front.emailTF4.setText(rs.getString("email"));
            }
            else{
                Front.idL4.setDisable(false);
                Front.idTF4.setDisable(false);
                Front.searchButton4.setDisable(false);
                Front.firstnameTF4.clear();
                Front.lastnameTF4.clear();
                Front.ssnTF4.clear();
                Front.adressTF4.clear();
                Front.phone_numberTF4.clear();
                Front.emailTF4.clear();

                Front.firstnameTF4.setDisable(true);
                Front.lastnameTF4.setDisable(true);
                Front.ssnTF4.setDisable(true);
                Front.adressTF4.setDisable(true);
                Front.phone_numberTF4.setDisable(true);
                Front.emailTF4.setDisable(true);
                Front.firstnameL4.setDisable(true);
                Front.lastnameL4.setDisable(true);
                Front.ssnL4.setDisable(true);
                Front.adressL4.setDisable(true);
                Front.phone_numberL4.setDisable(true);
                Front.emailL4.setDisable(true);
                Front.deleteButton4.setDisable(true);
                Front.changeUserButton4.setDisable(true);



                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You must enter valid user ID! ");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            Front.idL4.setDisable(false);
            Front.idTF4.setDisable(false);
            Front.searchButton4.setDisable(false);
            Front.firstnameTF4.clear();
            Front.lastnameTF4.clear();
            Front.ssnTF4.clear();
            Front.adressTF4.clear();
            Front.phone_numberTF4.clear();
            Front.emailTF4.clear();

            Front.firstnameTF4.setDisable(true);
            Front.lastnameTF4.setDisable(true);
            Front.ssnTF4.setDisable(true);
            Front.adressTF4.setDisable(true);
            Front.phone_numberTF4.setDisable(true);
            Front.emailTF4.setDisable(true);
            Front.firstnameL4.setDisable(true);
            Front.lastnameL4.setDisable(true);
            Front.ssnL4.setDisable(true);
            Front.adressL4.setDisable(true);
            Front.phone_numberL4.setDisable(true);
            Front.emailL4.setDisable(true);
            Front.deleteButton4.setDisable(true);
            Front.changeUserButton4.setDisable(true);



            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter valid user ID! ");

            alert.showAndWait();
        }
    }

    public void changeUserDeleting(){

        Front.idL4.setDisable(false);
        Front.idTF4.setDisable(false);
        Front.searchButton4.setDisable(false);

        Front.idTF4.clear();
        Front.firstnameTF4.clear();
        Front.lastnameTF4.clear();
        Front.ssnTF4.clear();
        Front.adressTF4.clear();
        Front.phone_numberTF4.clear();
        Front.emailTF4.clear();

        Front.firstnameTF4.setDisable(true);
        Front.lastnameTF4.setDisable(true);
        Front.ssnTF4.setDisable(true);
        Front.adressTF4.setDisable(true);
        Front.phone_numberTF4.setDisable(true);
        Front.emailTF4.setDisable(true);
        Front.firstnameL4.setDisable(true);
        Front.lastnameL4.setDisable(true);
        Front.ssnL4.setDisable(true);
        Front.adressL4.setDisable(true);
        Front.phone_numberL4.setDisable(true);
        Front.emailL4.setDisable(true);
        Front.deleteButton4.setDisable(true);
        Front.changeUserButton4.setDisable(true);

    }

    public void finalDeleting(){

        String sql ="DELETE FROM Korisnik WHERE id_korisnika='"+Front.idTF4.getText()+"';";

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        if(Front.idTF4.getText().equals("")){

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter valid user ID, try again!.");

            alert.showAndWait();

        }else{

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You successfully.");

            alert.showAndWait();
        }

        Front.idTF4.clear();
        Front.firstnameTF4.clear();
        Front.lastnameTF4.clear();
        Front.ssnTF4.clear();
        Front.adressTF4.clear();
        Front.phone_numberTF4.clear();
        Front.emailTF4.clear();

        Front.idL4.setDisable(false);
        Front.idTF4.setDisable(false);
        Front.searchButton4.setDisable(false);

        Front.firstnameTF4.setDisable(true);
        Front.lastnameTF4.setDisable(true);
        Front.ssnTF4.setDisable(true);
        Front.adressTF4.setDisable(true);
        Front.phone_numberTF4.setDisable(true);
        Front.emailTF4.setDisable(true);
        Front.firstnameL4.setDisable(true);
        Front.lastnameL4.setDisable(true);
        Front.ssnL4.setDisable(true);
        Front.adressL4.setDisable(true);
        Front.phone_numberL4.setDisable(true);
        Front.emailL4.setDisable(true);
        Front.deleteButton4.setDisable(true);
        Front.changeUserButton4.setDisable(true);
    }


    //Navigation User.Show_all_users --------------------------------------------------------------------------------------------------------------------	
    public void showAllUsers(){
        String sql = "SELECT * FROM Korisnik;";

        try (Connection conn = this.connectionWithDatabase();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            Front.usersData.clear();

            while (rs.next()) {
                Front.usersData.add(new User( String.valueOf(rs.getString("id_korisnika")), rs.getString("ime"), rs.getString("phone"), rs.getString("email") ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



//Movies *

    //Navigation Movies.Add_movie --------------------------------------------------------------------------------------------------------------------
    public void dodajFilm(){

        String sql = "INSERT INTO Film (naslov,zanr,opis,godina_izdanja,jezik,ocena_imdb,trajanje_minuti,glavni_glumci) VALUES('"+Front.movieTitleTF.getText()+"','"+Front.genreTF.getText()+"','"+Front.descriptionTF.getText()+"','"+Front.release_yearTF.getText()+"','"+Front.languageTF.getText()+"',"+Front.rating_imdbTF.getText()+",'"+Front.duration_minutesTF.getText()+"','"+Front.main_actorsTF.getText()+"')";


        if( Front.movieTitleTF.getText().equals("") || Front.genreTF.getText().equals("") || Front.release_yearTF.getText().equals("") || Front.rating_imdbTF.getText().equals("") || Front.duration_minutesTF.getText().equals("") ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter all fields with * ");

            alert.showAndWait();
        }
        else{

            try {
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully added new movie.");
            alert.showAndWait();

            Front.movieTitleTF.clear();
            Front.genreTF.clear();
            Front.descriptionTF.clear();
            Front.release_yearTF.clear();
            Front.languageTF.clear();
            Front.rating_imdbTF.clear();
            Front.duration_minutesTF.clear();
            Front.main_actorsTF.clear();
        }
    }


    //Navigation Movies.Find_movie --------------------------------------------------------------------------------------------------------------------	
    public void checkAllSearchFields(){

        if(Front.find_idCB.isSelected()==true){
            Front.find_idTF.setDisable(false);
        }else{
            Front.find_idTF.clear();
            Front.find_idTF.setDisable(true);
        }

        if(Front.find_titleCB.isSelected()==true){
            Front.find_titleTF.setDisable(false);
        }else{
            Front.find_titleTF.setDisable(true);
            Front.find_titleTF.clear();
        }

        if(Front.find_genreCB.isSelected()==true){
            Front.find_genreTF.setDisable(false);
        }else{
            Front.find_genreTF.setDisable(true);
            Front.find_genreTF.clear();
        }

        if(Front.find_release_yearCB.isSelected()==true){
            Front.find_release_yearTF.setDisable(false);
        }else{
            Front.find_release_yearTF.setDisable(true);
            Front.find_release_yearTF.clear();
        }

        if(Front.find_main_actorCB.isSelected()==true){
            Front.find_main_actorTF.setDisable(false);
        }else{
            Front.find_main_actorTF.setDisable(true);
            Front.find_main_actorTF.clear();
        }

    }


    public void findMovie(){


        if( Front.find_idTF.getText().equals("") && Front.find_titleTF.getText().equals("") && Front.find_genreTF.getText().equals("") && Front.find_release_yearTF.getText().equals("") && Front.find_main_actorTF.getText().equals("") ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter at least one field!");

            alert.showAndWait();
        }
        else{

            String sql = "SELECT * FROM Film WHERE ";
            if(Front.find_idCB.isSelected()==true){
                sql = sql + "id="+Front.find_idTF.getText()+" AND ";
            }
            if(Front.find_titleCB.isSelected()==true){
                sql = sql +"naslov LIKE '%"+Front.find_titleTF.getText()+"%'"+" AND ";
            }
            if(Front.find_genreCB.isSelected()==true){
                sql = sql +"zanr LIKE '%"+Front.find_genreTF.getText()+"%'"+" AND ";
            }
            if(Front.find_release_yearCB.isSelected()==true){
                sql = sql +"godina_izdanja="+Front.find_release_yearTF.getText()+" AND ";
            }
            if(Front.find_main_actorCB.isSelected()==true){
                sql = sql +"glavni_glumci LIKE '%"+Front.find_main_actorTF.getText()+"%'"+" AND ";
            }


            String sql2 = sql.substring(0, sql.length()-4);

            try (
                    Connection conn = this.connectionWithDatabase();
                    Statement stmt  = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql2);
            ){

                Front.movieData2.clear();

                while (rs.next()) {

                    Front.movieData2.add(new Movie( String.valueOf(rs.getInt("id")), rs.getString("naslov"), rs.getString("zanr"), rs.getString("opis"), String.valueOf(rs.getInt("godina_izdanja")), rs.getString("jezik"), String.valueOf(rs.getDouble("ocena_imdb")), rs.getString("trajanje_minuti"), rs.getString("glavni_glumci"), rs.getString("dostupno"), String.valueOf(rs.getInt("poslednji_iznajmio")), rs.getString("poslednji_datum_iznajmljivanja")  ));

                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
    }


    //Navigation Movies.Edit/Delete_movie --------------------------------------------------------------------------------------------------------------------	
    public void allowMovieEdit(){

        String sql ="SELECT * FROM Film WHERE id="+Front.edit_idTF2.getText()+";";

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            if(rs.next()==true){
                Front.edit_idL2.setDisable(true);
                Front.edit_idTF2.setDisable(true);
                Front.edit_searchButton2.setDisable(true);

                Front.movieTitleL2.setDisable(false);
                Front.movieTitleTF2.setDisable(false);
                Front.genreL2.setDisable(false);
                Front.genreTF2.setDisable(false);
                Front.descriptionL2.setDisable(false);
                Front.descriptionTF2.setDisable(false);
                Front.release_yearL2.setDisable(false);
                Front.release_yearTF2.setDisable(false);
                Front.languageL2.setDisable(false);
                Front.languageTF2.setDisable(false);
                Front.rating_imdbL2.setDisable(false);
                Front.rating_imdbTF2.setDisable(false);
                Front.duration_minutesL2.setDisable(false);
                Front.duration_minutesTF2.setDisable(false);
                Front.main_actorsL2.setDisable(false);
                Front.main_actorsTF2.setDisable(false);
                Front.edit_deleteButton.setDisable(false);


                Front.movieTitleTF2.setText(rs.getString("naslov"));
                Front.genreTF2.setText(rs.getString("zanr"));
                Front.descriptionTF2.setText(rs.getString("opis"));
                Front.release_yearTF2.setText(rs.getString("godina_izdanja"));
                Front.languageTF2.setText(rs.getString("jezik"));
                Front.rating_imdbTF2.setText(String.valueOf(rs.getDouble("ocena_imdb")));
                Front.duration_minutesTF2.setText(rs.getString("trajanje_minuti"));
                Front.main_actorsTF2.setText(rs.getString("glavni_glumci"));

                Front.edit_change_movieButton.setDisable(false);
                Front.edit_fDugme2.setDisable(false);
            }
            else{
                Front.edit_idL2.setDisable(false);
                Front.edit_idTF2.setDisable(false);
                Front.edit_searchButton2.setDisable(false);

                Front.movieTitleTF2.clear();
                Front.genreTF2.clear();
                Front.descriptionTF2.clear();
                Front.release_yearTF2.clear();
                Front.languageTF2.clear();
                Front.rating_imdbTF2.clear();
                Front.duration_minutesTF2.clear();
                Front.main_actorsTF2.clear();

                Front.movieTitleL2.setDisable(true);
                Front.movieTitleTF2.setDisable(true);
                Front.genreL2.setDisable(true);
                Front.genreTF2.setDisable(true);
                Front.descriptionL2.setDisable(true);
                Front.descriptionTF2.setDisable(true);
                Front.release_yearL2.setDisable(true);
                Front.release_yearTF2.setDisable(true);
                Front.languageL2.setDisable(true);
                Front.languageTF2.setDisable(true);
                Front.rating_imdbL2.setDisable(true);
                Front.rating_imdbTF2.setDisable(true);
                Front.duration_minutesL2.setDisable(true);
                Front.duration_minutesTF2.setDisable(true);
                Front.main_actorsL2.setDisable(true);
                Front.main_actorsTF2.setDisable(true);
                Front.edit_deleteButton.setDisable(true);



                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You must enter valid Movie ID! ");

                alert.showAndWait();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            Front.edit_idL2.setDisable(false);
            Front.edit_idTF2.setDisable(false);
            Front.edit_searchButton2.setDisable(false);

            Front.movieTitleTF2.clear();
            Front.genreTF2.clear();
            Front.descriptionTF2.clear();
            Front.release_yearTF2.clear();
            Front.languageTF2.clear();
            Front.rating_imdbTF2.clear();
            Front.duration_minutesTF2.clear();
            Front.main_actorsTF2.clear();

            Front.movieTitleL2.setDisable(true);
            Front.movieTitleTF2.setDisable(true);
            Front.genreL2.setDisable(true);
            Front.genreTF2.setDisable(true);
            Front.descriptionL2.setDisable(true);
            Front.descriptionTF2.setDisable(true);
            Front.release_yearL2.setDisable(true);
            Front.release_yearTF2.setDisable(true);
            Front.languageL2.setDisable(true);
            Front.languageTF2.setDisable(true);
            Front.rating_imdbL2.setDisable(true);
            Front.rating_imdbTF2.setDisable(true);
            Front.duration_minutesL2.setDisable(true);
            Front.duration_minutesTF2.setDisable(true);
            Front.main_actorsL2.setDisable(true);
            Front.main_actorsTF2.setDisable(true);
            Front.edit_deleteButton.setDisable(true);



            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter valid Movie ID! ");

            alert.showAndWait();
        }

    }

    public void change_movie_EditingMovie(){

        Front.edit_idL2.setDisable(false);
        Front.edit_idTF2.setDisable(false);
        Front.edit_searchButton2.setDisable(false);

        Front.edit_idTF2.clear();
        Front.movieTitleTF2.clear();
        Front.genreTF2.clear();
        Front.descriptionTF2.clear();
        Front.release_yearTF2.clear();
        Front.languageTF2.clear();
        Front.rating_imdbTF2.clear();
        Front.duration_minutesTF2.clear();
        Front.main_actorsTF2.clear();

        Front.movieTitleL2.setDisable(true);
        Front.movieTitleTF2.setDisable(true);
        Front.genreL2.setDisable(true);
        Front.genreTF2.setDisable(true);
        Front.descriptionL2.setDisable(true);
        Front.descriptionTF2.setDisable(true);
        Front.release_yearL2.setDisable(true);
        Front.release_yearTF2.setDisable(true);
        Front.languageL2.setDisable(true);
        Front.languageTF2.setDisable(true);
        Front.rating_imdbL2.setDisable(true);
        Front.rating_imdbTF2.setDisable(true);
        Front.duration_minutesL2.setDisable(true);
        Front.duration_minutesTF2.setDisable(true);
        Front.main_actorsL2.setDisable(true);
        Front.main_actorsTF2.setDisable(true);
        Front.edit_change_movieButton.setDisable(true);
        Front.edit_fDugme2.setDisable(true);
        Front.edit_deleteButton.setDisable(true);

    }

    public void finalEditMovie(){

        String sql = "UPDATE Film SET naslov='"+Front.movieTitleTF2.getText()+"',zanr='"+Front.genreTF2.getText()+"',opis='"+Front.descriptionTF2.getText()+"',godina_izdanja="+Front.release_yearTF2.getText()+",jezik='"+Front.languageTF2.getText()+"',ocena_imdb="+Front.rating_imdbTF2.getText()+",trajanje_minuti="+Front.duration_minutesTF2.getText()+",glavni_glumci='"+Front.main_actorsTF2.getText()+"' WHERE id="+Front.edit_idTF2.getText()+";";

        if( Front.movieTitleTF2.getText().equals("") || Front.genreTF2.getText().equals("") || Front.release_yearTF2.getText().equals("") || Front.rating_imdbTF2.getText().equals("") || Front.duration_minutesTF2.equals("") ){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter all fields with * ");

            alert.showAndWait();
        }
        else{


            try {
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                stmt.executeUpdate(sql);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You hav esuccessfully edited selected movie.");
            alert.showAndWait();

            Front.edit_idL2.setDisable(false);
            Front.edit_idTF2.setDisable(false);
            Front.edit_searchButton2.setDisable(false);

            Front.edit_idTF2.clear();
            Front.movieTitleTF2.clear();
            Front.genreTF2.clear();
            Front.descriptionTF2.clear();
            Front.release_yearTF2.clear();
            Front.languageTF2.clear();
            Front.rating_imdbTF2.clear();
            Front.duration_minutesTF2.clear();
            Front.main_actorsTF2.clear();

            Front.movieTitleL2.setDisable(true);
            Front.movieTitleTF2.setDisable(true);
            Front.genreL2.setDisable(true);
            Front.genreTF2.setDisable(true);
            Front.descriptionL2.setDisable(true);
            Front.descriptionTF2.setDisable(true);
            Front.release_yearL2.setDisable(true);
            Front.release_yearTF2.setDisable(true);
            Front.languageL2.setDisable(true);
            Front.languageTF2.setDisable(true);
            Front.rating_imdbL2.setDisable(true);
            Front.rating_imdbTF2.setDisable(true);
            Front.duration_minutesL2.setDisable(true);
            Front.duration_minutesTF2.setDisable(true);
            Front.main_actorsL2.setDisable(true);
            Front.main_actorsTF2.setDisable(true);
            Front.edit_change_movieButton.setDisable(true);
            Front.edit_fDugme2.setDisable(true);
            Front.edit_deleteButton.setDisable(true);

        }

    }

    public void deleteMovie(){

        String sql = "DELETE FROM Film WHERE id="+Front.edit_idTF2.getText()+";";


        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("You have successfully deleted selected Movie.");
        alert.showAndWait();

        Front.edit_idL2.setDisable(false);
        Front.edit_idTF2.setDisable(false);
        Front.edit_searchButton2.setDisable(false);

        Front.edit_idTF2.clear();
        Front.movieTitleTF2.clear();
        Front.genreTF2.clear();
        Front.descriptionTF2.clear();
        Front.release_yearTF2.clear();
        Front.languageTF2.clear();
        Front.rating_imdbTF2.clear();
        Front.duration_minutesTF2.clear();
        Front.main_actorsTF2.clear();

        Front.movieTitleL2.setDisable(true);
        Front.movieTitleTF2.setDisable(true);
        Front.genreL2.setDisable(true);
        Front.genreTF2.setDisable(true);
        Front.descriptionL2.setDisable(true);
        Front.descriptionTF2.setDisable(true);
        Front.release_yearL2.setDisable(true);
        Front.release_yearTF2.setDisable(true);
        Front.languageL2.setDisable(true);
        Front.languageTF2.setDisable(true);
        Front.rating_imdbL2.setDisable(true);
        Front.rating_imdbTF2.setDisable(true);
        Front.duration_minutesL2.setDisable(true);
        Front.duration_minutesTF2.setDisable(true);
        Front.main_actorsL2.setDisable(true);
        Front.main_actorsTF2.setDisable(true);
        Front.edit_change_movieButton.setDisable(true);
        Front.edit_fDugme2.setDisable(true);
        Front.edit_deleteButton.setDisable(true);


    }


    //Navigation Movies.Show_all_movies -----------------------------------------------------------------------------------------------------------------------	
    public void show_all_movies(){

        String sql = "SELECT * FROM Film;";

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            Front.movieData3.clear();

            while (rs.next()) {

                Front.movieData3.add(new Movie( String.valueOf(rs.getInt("id")), rs.getString("naslov"), rs.getString("zanr"), rs.getString("opis"), rs.getString("godina_izdanja"), rs.getString("jezik"), String.valueOf(rs.getDouble("ocena_imdb")), rs.getString("trajanje_minuti"), rs.getString("glavni_glumci"), rs.getString("dostupno"), rs.getString("poslednji_iznajmio"), rs.getString("poslednji_datum_iznajmljivanja")  ));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


//Navigation Rental * --------------------------------------------------------------------------------------------------------------------

    //Navigation Rental.Rental_movie --------------------------------------------------------------------------------------------------------------------
    public boolean check_is_movie_availbale(){

        String sql = "SELECT * FROM Film WHERE id=" +Front.rentalMovie_id_movieTF.getText() +";";
        boolean rezultat = false;

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                if(rs.getString("rezervisano").equals("da")){
                    rezultat = true;
                }
            }
        }  catch (SQLException c) {
            System.out.println(c.getMessage());
        }


        return rezultat;
    }

    public boolean checkIfPersonWhoReservedTheMovieTakeThatMovie(){

        String sql = "SELECT * FROM Film WHERE id=" +Front.rentalMovie_id_movieTF.getText() +";";
        boolean rezultat = false;

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                if(Front.rentalMovie_id_userTF.getText().equals(rs.getString("rezervisao_id"))){
                    rezultat = true;
                }
            }
        }  catch (SQLException c) {
            System.out.println(c.getMessage());
        }

        return rezultat;
    }



    public void finalRentalMovie(){

        String formatDatuma = "dd MM yyyy";
        String datumIznajmljivanja =new SimpleDateFormat(formatDatuma).format(new Date());


        String sql1 = "SELECT id FROM Film;";

        String sql2 = "SELECT dostupno FROM Film WHERE id="+Front.rentalMovie_id_movieTF.getText()+";";

        String sql3 = "UPDATE Film SET dostupno='ne', rezervisano='ne', poslednji_iznajmio='"+Front.rentalMovie_id_userTF.getText() + "', poslednji_datum_iznajmljivanja='"+ datumIznajmljivanja + "' WHERE id="+Front.rentalMovie_id_movieTF.getText()+";";

        String sql4 = "SELECT id_korisnika FROM Korisnik;";

        String idFilm = "";
        String dostupno = "";
        String idKorisnik = "";




        if(Front.rentalMovie_id_movieTF.getText().equals("") || Front.rentalMovie_id_userTF.getText().equals("") ){

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter all fields with * ");

            alert.showAndWait();

        }else{


            try (
                    Connection conn1 = this.connectionWithDatabase();
                    Statement stmt1  = conn1.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(sql1);
            ){

                while (rs1.next()) {
                    String a = String.valueOf(rs1.getInt("id"));

                    if( a.equals(Front.rentalMovie_id_movieTF.getText() ) ){

                        idFilm = a;
                    }

                }

            }  catch (SQLException c) {
                System.out.println(c.getMessage());

            }



            try (
                    Connection conn4 = this.connectionWithDatabase();
                    Statement stmt4  = conn4.createStatement();
                    ResultSet rs4 = stmt4.executeQuery(sql4);
            ){

                while (rs4.next()) {
                    String a = String.valueOf(rs4.getString("id_korisnika"));

                    if( a.equals(Front.rentalMovie_id_userTF.getText() ) ){

                        idKorisnik = a;
                    }

                }

            }  catch (SQLException c) {
                System.out.println(c.getMessage());

            }



            if(Front.rentalMovie_id_movieTF.getText().equals(idFilm) && Front.rentalMovie_id_userTF.getText().equals(idKorisnik)){


                try (   Connection conn2 = this.connectionWithDatabase();
                        Statement stmt2  = conn2.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                ){

                    while(rs2.next()){
                        if(rs2.getString("dostupno").equals("da")){
                            dostupno = "da";
                        }else{

                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Notification");
                            alert.setHeaderText(null);
                            alert.setContentText("You can't rental movie,because it's already rentaled!");

                            alert.showAndWait();

                            Front.rentalMovie_id_movieTF.clear();
                            Front.rentalMovie_id_userTF.clear();

                        }
                    }

                } catch (SQLException e) {
                    System.out.println(e.getMessage());

                }

            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You entered non-existent MOVIE ID or USER ID !");

                alert.showAndWait();

                Front.rentalMovie_id_movieTF.clear();
                Front.rentalMovie_id_userTF.clear();
            }


            if(dostupno.equals("da")){



                boolean passbookForBookReservation = false;

                boolean checkIsMovieAvailbale = this.check_is_movie_availbale();

                if(checkIsMovieAvailbale==true){
                    boolean proveriDaLiOsobaKojaJeRezervisalaFilmUzimaTajFilm = this.checkIfPersonWhoReservedTheMovieTakeThatMovie();

                    if(proveriDaLiOsobaKojaJeRezervisalaFilmUzimaTajFilm==false){
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Notification");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't rental movie because is ONLINE RESERVED.");

                        alert.showAndWait();

                        Front.rentalMovie_id_movieTF.clear();
                        Front.rentalMovie_id_userTF.clear();
                    }else{
                        passbookForBookReservation = true;
                    }
                }else{
                    passbookForBookReservation = true;
                }

                if(passbookForBookReservation==true){
                    try {
                        Connection conn3 = this.connectionWithDatabase();
                        Statement stmt3  = conn3.createStatement();
                        stmt3.executeUpdate(sql3);
                    }
                    catch(SQLException e){
                        System.out.println(e.getMessage());
                    };

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Notification");
                    alert.setHeaderText(null);
                    alert.setContentText("You have successfully rentaled movie. ");

                    alert.showAndWait();

                    Front.rentalMovie_id_movieTF.clear();
                    Front.rentalMovie_id_userTF.clear();
                }

            }

        }


    }



    //Navigation Rental.Return_back_movie --------------------------------------------------------------------------------------------------------------------	
    public void returnBackMovie() throws ParseException{

        String sql = "UPDATE Film SET dostupno= 'da' WHERE id="+Front.returnBackMovie_idMovieTF.getText()+";";

        String sql2 = "SELECT id FROM Film;";

        String sql3 = "SELECT dostupno FROM Film WHERE id="+Front.returnBackMovie_idMovieTF.getText()+";";

        String sql4 = "SELECT poslednji_datum_iznajmljivanja FROM Film WHERE id="+Front.returnBackMovie_idMovieTF.getText()+";";

        String movieID = "";
        String availbale = "";
        String dateOfRentalDatabase = null;
        int days = 0;


        if(Front.returnBackMovie_idMovieTF.getText().equals("") ){

            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You must enter all fields with * ");

            alert.showAndWait();

        }else{






            try (
                    Connection conn2 = this.connectionWithDatabase();
                    Statement stmt2  = conn2.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sql2);
            ){

                while (rs2.next()) {
                    String a = String.valueOf(rs2.getInt("id"));

                    if( a.equals(Front.returnBackMovie_idMovieTF.getText() ) ){

                        movieID = a;
                    }

                }

            }  catch (SQLException c) {
                System.out.println(c.getMessage());

            }




            if(Front.returnBackMovie_idMovieTF.getText().equals(movieID)){

                try (
                        Connection conn3 = this.connectionWithDatabase();
                        Statement stmt3  = conn3.createStatement();
                        ResultSet rs3 = stmt3.executeQuery(sql3);
                ){
                    String a="";

                    while(rs3.next()){
                        a = rs3.getString("dostupno");
                    }

                    if( a.equals("ne") ){

                        availbale = "ne";


                    }else{
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Notification");
                        alert.setHeaderText(null);
                        alert.setContentText("This movie is already returned! ");

                        alert.showAndWait();

                        Front.returnBackMovie_idMovieTF.clear();
                    }



                }  catch (SQLException c) {
                    System.out.println(c.getMessage());

                }

            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You entered invalid Movie ID! ");

                alert.showAndWait();
            }

        }

        if(availbale.equals("ne")){


            try (
                    Connection conn = this.connectionWithDatabase();
                    Statement stmt  = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql4);
            ){

                //Check is movie returned on time,if movie is not returned on time then calculating how much days is overdated them calculate how much money user must pay for overdate   	
                while(rs.next()){
                    dateOfRentalDatabase = rs.getString("poslednji_datum_iznajmljivanja");
                }

                SimpleDateFormat formatDatuma = new SimpleDateFormat("dd MM yyyy");

                Date datumIznajmljivanja = formatDatuma.parse(dateOfRentalDatabase);
                Date datumVracanja = new java.util.Date();

                long razlika = datumVracanja.getTime() - datumIznajmljivanja.getTime();
                days = (int) TimeUnit.DAYS.convert(razlika, TimeUnit.MILLISECONDS);


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            if(days==0 || days==1 || days==2 || days==3 || days==4 || days==5){

            }else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Obaveštenje");
                alert.setHeaderText(null);
                int exceededDays = days-5;
                double exceededMoney = exceededDays * 5;
                alert.setContentText("You have exceeded 5 days of allowed rental.\nMovie is rentaled(day month year) "+dateOfRentalDatabase+" .\n"+"You must pay for "+exceededDays+" prekoračenih dana,5 dinara po danu, tj. "+exceededMoney + " dollars." );

                alert.showAndWait();
            }




            try {
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                stmt.executeUpdate(sql);

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully returned back movie. ");

            alert.showAndWait();

            Front.returnBackMovie_idMovieTF.clear();
        }

    }



    //Navigation Rental.List_of_rental_movies --------------------------------------------------------------------------------------------------------------------	
    public void showRentaledMovies(){

        String sql = "SELECT * FROM Film WHERE dostupno='ne';";

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            Front.movieData4.clear();

            while (rs.next()) {

                Front.movieData4.add(new Movie( String.valueOf(rs.getInt("id")), rs.getString("naslov"), rs.getString("zanr"), rs.getString("opis"), String.valueOf(rs.getInt("godina_izdanja")), rs.getString("jezik"), String.valueOf(rs.getDouble("ocena_imdb")), rs.getString("trajanje_minuti"), rs.getString("glavni_glumci"), rs.getString("dostupno"), String.valueOf(rs.getString("poslednji_iznajmio")), rs.getString("poslednji_datum_iznajmljivanja")  ));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }



//Navigation Properties * --------------------------------------------------------------------------------------------------------------------	

    //Navigation Properties.Security_code --------------------------------------------------------------------------------------------------------------------	
    public void changeSecurityCode(){

        String sql = "SELECT kod FROM SigurnosniKod WHERE id=1;";
        String sql2 =  "UPDATE SigurnosniKod SET kod='"+Front.properties_newPW.getText()+"' WHERE id=1;";
        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            if(Front.properties_currentPF.getText().equals("") || Front.properties_newPW.getText().equals("") || Front.properties_repeat_newPF.getText().equals("") ){

                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Notification");
                alert.setHeaderText(null);
                alert.setContentText("You must enter all fields with * ");

                alert.showAndWait();

            }else{

                while(rs.next()){
                    if(Front.properties_currentPF.getText().equals(rs.getString("kod"))){

                        if(Front.properties_newPW.getText().equals(Front.properties_repeat_newPF.getText())){

                            try {
                                Connection conn2 = this.connectionWithDatabase();
                                Statement stmt2  = conn.createStatement();
                                stmt2.executeUpdate(sql2);

                                Front.securityCode = Front.properties_newPW.getText();

                            }catch (SQLException s) {
                                System.out.println(s.getMessage());
                            }

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Notification");
                            alert.setHeaderText(null);
                            alert.setContentText("You have successfully changed Security code.\nPlease login again.");

                            alert.showAndWait();

                            Front.properties_currentPF.clear();
                            Front.properties_newPW.clear();
                            Front.properties_repeat_newPF.clear();

                            Front.startingStage.setScene(Front.security_codeScene);
                            Front.startingStage.centerOnScreen();

                        }else{
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Notification");
                            alert.setHeaderText(null);
                            alert.setContentText("The fields NEW SECURITY CODE and REPEAT NEW SECURITY CODE!");

                            alert.showAndWait();

                            Front.properties_currentPF.clear();
                            Front.properties_newPW.clear();
                            Front.properties_repeat_newPF.clear();

                        }


                    }else{
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Notification");
                        alert.setHeaderText(null);
                        alert.setContentText("You entered wrong Current security code! ");

                        alert.showAndWait();

                        Front.properties_currentPF.clear();
                        Front.properties_newPW.clear();
                        Front.properties_repeat_newPF.clear();
                    }
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String returnBackCurrentSecurityCode(){

        String sql = "SELECT kod FROM SigurnosniKod WHERE id=1;";
        String securityCode=null;

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()){
                securityCode = rs.getString("kod");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return securityCode;
    }



    //Navigation Properties.Change_background --------------------------------------------------------------------------------------------------------------------	
    public void returnBackBackground(){

        String sql = "SELECT pozadina FROM Pozadina WHERE id=1;";

        try (
                Connection conn = this.connectionWithDatabase();
                Statement stmt  = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){

            while(rs.next()){
                Front.background = rs.getString("pozadina");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }


    public void changeBackground(){

        String sql =  "UPDATE Pozadina SET pozadina='"+Front.background+"' WHERE id=1;";

        try {
            Connection conn = this.connectionWithDatabase();
            Statement stmt  = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------       

    }

}