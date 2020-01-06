<?php 
	require_once 'dbconnectapi.php';
	
	$response = array();
	
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
			case 'signup':
				if(isTheseParametersAvailable(array('name','email','password'))){
					$name = $_POST['name']; 
					$email = $_POST['email']; 
					$password = ($_POST['password']);
					
					$stmt = $conn->prepare("SELECT id FROM user WHERE email = ?");
					$stmt->bind_param("s", $email);
					$stmt->execute();
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						$response['error'] = true;
						$response['message'] = 'User already registered';
						$stmt->close();
					}else{
						$stmt = $conn->prepare("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
						$stmt->bind_param("sss", $name, $email, $password);
						if($stmt->execute()){
							$stmt = $conn->prepare("SELECT id, id, name, email FROM user WHERE email = ?"); 
							$stmt->bind_param("s",$email);
							$stmt->execute();
							$stmt->bind_result($userid, $id, $name, $email);
							$stmt->fetch();
							
							$user = array(
								'id'=>$id, 
								'name'=>$name, 
								'email'=>$email
							);
							
							$stmt->close();
							
							$response['error'] = false; 
							$response['message'] = 'User registered successfully'; 
							$response['user'] = $user; 
						}
					}
					
				}else{
					$response['error'] = true; 
					$response['message'] = 'required parameters are not available'; 
				}
				
			break; 
			
			case 'login':
				
				if(isTheseParametersAvailable(array('email','password'))){
					
					$email = $_POST['email'];
					$password = ($_POST['password']); 
					
					$stmt = $conn->prepare("SELECT id, name, email FROM user WHERE email = ? AND password = ? ");
					$stmt->bind_param("ss",$email, $password);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $name, $email);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'name'=>$name, 
							'email'=>$email
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid email or password';
					}
				}
			break; 
			
			default: 
				$response['error'] = true; 
				$response['message'] = 'Invalid Operation Called';
		}
		
	}else{
		$response['error'] = true; 
		$response['message'] = 'Invalid API Call';
	}
	
	echo json_encode($response);
	
	function isTheseParametersAvailable($params){
		
		foreach($params as $param){
			if(!isset($_POST[$param])){
				return false; 
			}
		}
		return true; 
	}