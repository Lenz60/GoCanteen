<?php 
	require_once 'dbconnectapi.php';
	
	$response = array();
	
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
			case 'signup':
				if(isTheseParametersAvailable(array('nim','email','nama','password','gender'))){
					$nim = $_POST['nim']; 
					$email = $_POST['email']; 
					$nama = $_POST['nama']; 
					$password = ($_POST['password']);
					$gender = $_POST['gender']; 
					
					$stmt = $conn->prepare("SELECT id FROM users WHERE nim = ? OR email = ?");
					$stmt->bind_param("ss", $nim, $email);
					$stmt->execute();
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						$response['error'] = true;
						$response['message'] = 'User already registered';
						$stmt->close();
					}else{
						$stmt = $conn->prepare("INSERT INTO users (nim, email, nama, password, gender) VALUES (?, ?, ?, ?, ?)");
						$stmt->bind_param("sssss", $nim, $email, $nama, $password, $gender);
						if($stmt->execute()){
							$stmt = $conn->prepare("SELECT id, id, nim, email, nama, gender FROM users WHERE nim = ?"); 
							$stmt->bind_param("s",$nim);
							$stmt->execute();
							$stmt->bind_result($userid, $id, $nim, $email, $nama, $gender);
							$stmt->fetch();
							
							$user = array(
								'id'=>$id, 
								'nim'=>$nim, 
								'email'=>$email,
								'nama'=>$nama,
								'gender'=>$gender
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
				
				if(isTheseParametersAvailable(array('nim', 'password'))){
					
					$nim = $_POST['nim'];
					$password = md5($_POST['password']); 
					
					$stmt = $conn->prepare("SELECT id, nim, email, nama, gender FROM users WHERE nim = ? AND password = ?");
					$stmt->bind_param("ss",$nim, $password);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $nim, $email, $nama, $gender);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'nim'=>$nim, 
							'email'=>$email,
							'nama'=>$nama,
							'gender'=>$gender
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid nim or password';
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