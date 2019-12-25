# READ ME FIRST

########################################################################################
## WHAT TO DO FIRST
1. Import the mysql Database, the .sql is located at QueryDB folder
2. Copy the `api.php` and `dbconnectapi.php` to your localhost directory
3. If you want to test the API, use POSTMAN
    - Use it like this : 
       > **For Register** `localhost/api.php?apicall=signup`
       
       > **For login** `localhost/api.php?apicall=register`
4. Check your ipv4 at `ipconfig -all` using cmd
5. Change the url at the Android Studio to your ipv4
    - Use it like this : 
      >**For Register** `http://*yourIP*/api.php?apicall=signup`
      
      >**For login** `http://*yourIP*/api.php?apicall=register`
6. Use Laravel to check the table `php artisan serve` at folder **Laravel**
7. Check the users table at this route `localhost:8000/pembeli` or `http://127.0.0.1:8000/pembeli/`
8. Then You are done 
########################################################################################

There is 2 different method that successfully coded with Login & Register 

1. ##**GoCanteen 0.1**
    - This Project is first coded project that using Volley lib as android library, this project already have logout function in it 
      so we can just modify the UI 
    -if you want to use it just change the IP at URLs.java and change it with your ipv4
2. ##**GoCanteen 1.5**
    - This Project is the last coded project that using Retrofit lib as android library, this project **don't have** logout function in it,
      just simple Login and Register 
    -if you want to use it just change the IP at UtilsApi.java and change it with your ipv4
    
##TODO List
- [x] Create Login & Register
- [x] Create Logout session (done in GoCanteen 0.1)
- [ ] Create animated splash screen (Require Logo)
- [ ] Finish Up the Database
- [ ] Create Button Navigation Bar
   - [ ] Create Notification page 
   - [ ] Create Profile page
   - [ ] Create Checkout page
   - [ ] Create Canteen Stall page
   - [ ] Create Home Page
- [ ] Create Query Logic for Recomendated Food

