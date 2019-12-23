<?php
//
  

    $conn=mysqli_connect("localhost","root","") or exit ("Gagal Koneksi DB.");
    mysqli_select_db($conn,"db_gocanteen")or exit ("Database not found");


    //array display response
    $response = array();

    //if it is an api call 
    //that means a get parameter named api call is set in the URL 
    //and with this parameter we are concluding that it is an api call 

    if(isset($_GET['apicall'])){
        switch($_GET['apicall']){
            case 'register' :
                //code for register
                //checking the parameters required are available or not
                if(isTheseParametersAvailable(array('NIM','nama','email','password','gender'))){

                    //getting values
                    $username = $_POST['NIM'];
                    $nama = $_POST['nama'];
                    $email = $_POST['email'];
                    $password = sha1($_POST['password']);
                    $gender = $_POST['gender'];

                    $stmt = $conn->prepare('SELECT id FROM users WHERE NIM = ? OR email = ?');
                    $stmt-> bind_Param("ss",$NIM,$email);
                    $stmt->execute();
                    $stmt->store_result();

                    //if user already exist in database 
                    if($stmt->num_rows > 0 ){
                        $response['error'] = true;
                        $response['message'] = 'User already registred';
                        $stmt->close();
                    }
                    else{
                        //if user is  new creating an insert query
                        $stmt = $conn->prepare("INSERT INTO users (NIM, nama, email, password, gender) VALUES (?,?,?,?,?)");
                        $stmt->bind_param("sssss",$NIM,$nama,$email,$password,$gender);

                        //if user added succesfully to database
                        if($stmt->execute()){
                            
                            //fetching the user back
                            $stmt = $conn->prepare("SELECT id, id, NIM, nama, email, gender FROM users WHERE NIM = ?");
                            $stmt->bind_param("s",$NIM);
                            $stmt->execute();
                            $stmt->bind_result($userid,$id,$NIM,$nama,$email,$gender);
                            $stmt->fetch();

                            $user = array(
                                'id'=>$id,
                                'NIM'=>$nim,
                                'nama'=>$nama,
                                'email'=>$email,
                                'gender'=>$gender
                            );

                            $stmt->close();

                            //adding user data in response
                            $response['error'] = false;
                            $response['message'] = 'User registered successfully';
                            $response['user'] = $user;
                        }
                    }
                }
                else {
                    $response['error']=true;
                    $response['message'] = 'required parameters are not available';
                }

            break;

            case 'login':
                //code for login
                //for login we need the username and password 
				if(isTheseParametersAvailable(array('NIM', 'password'))){
					//getting values 
					$username = $_POST['NIM'];
					$password = sha1($_POST['password']); 
					
					//creating the query 
					$stmt = $conn->prepare("SELECT id, NIM, nama, email, gender FROM users WHERE NIM = ? AND password = ?");
					$stmt->bind_param("ss",$NIM, $password);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					//if the user exist with given credentials 
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $NIM, $email, $gender);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'NIM'=>$username, 
							'email'=>$email,
							'nama'=>$nama,
							'gender'=>$gender
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						//if the user not found 
						$response['error'] = false; 
						$response['message'] = 'Invalid username or password';
					}
				}

            break;

            default:
            $response['error'] = true;
            $response['message'] = 'Invalid Operation Called';
        }
    }
    else{
        //if it is not api call 
        //pushing appropriate values to response array 
        $response['error'] = true; 
        $response['message'] = 'Invalid API Call';
    }
    //displaying the response in json structure 
    echo json_encode($response);


        //function validating all the paramters are available
        //we will pass the required parameters to this function 
    function isTheseParametersAvailable($params){
        
        //traversing through all the parameters 
        foreach($params as $param){
            //if the paramter is not available
            if(!isset($_POST[$param])){
                //return false 
                return false; 
            }
        }
        //return true if every param is available 
        return true; 
    }