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

						"INSERT INTO User(name,email,password) VALUES(?,?,?);
						INSERT INTO Saldo(id_user, saldo) VALUES(LAST_INSERT_ID(),'0') ;";
						//INSERT INTO user (name, email, password) VALUES (?, ?, ?)
						$stmt = $conn->prepare("INSERT INTO user (name, email, password) VALUES (?, ?, ?)");
						$stmt->bind_param("sss", $name, $email, $password);
						if($stmt->execute()){
							$stmt = $conn->prepare("INSERT INTO saldo(id_user, saldo) VALUES(LAST_INSERT_ID(),'0')");
							$stmt->execute();
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
					
					$stmt = $conn->prepare("SELECT id, name, email, saldo FROM user JOIN saldo ON user.id = saldo.id_user WHERE email = ? AND password = ? ");
					$stmt->bind_param("ss",$email, $password);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $name, $email, $saldo);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'name'=>$name, 
							'email'=>$email,
							'saldo'=>$saldo
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

			case 'topup':
				
				if(isTheseParametersAvailable(array('code', 'id'))){
					
					$code = ($_POST['code']); 
					$user_id = ($_POST['id']); 
					
					$stmt = $conn->prepare("SELECT id, amount, code FROM voucher WHERE code = ?");
					$stmt->bind_param("s",$code);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $amount, $code);
						$stmt->fetch();
						
						$voucher = array(
							'id'=>$id, 
							'amount'=>$amount, 
							'code'=>$code,
						);

						$response['error'] = false; 
						$response['message'] = 'Code Matched';
						$response['voucher'] = $voucher; 
						 

						
						if($code == '8ba6ee30a5'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '10000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 10.000';
						}
						else if ($code == 'c2ac15f919'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '20000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 20.000';
							
						}
						else if ($code == 'a80ed0fd0d'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '50000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 50.000';
						}
						else if ($code == '54a24cbf5f'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '100000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 100.000';
						}
						else if ($code == 'debc8d1486'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '200000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 200.000';
						}
						else if ($code == '66d9228218'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '300000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 300.000';
						}
						else if ($code == '2386bb23c5'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '400000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 400.000';
						}
						else if ($code == '7b3f6bf1e5'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '500000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 500.000';
						}
					

						$stmt = $conn->prepare("SELECT id_user, saldo FROM saldo WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();

							$stmt->bind_result($id_user, $saldo);
							$stmt->fetch();
							
							$viewsaldo = array(
								'id_user'=>$id_user, 
								'saldo'=>$saldo, 
							);
							$response['saldo'] = $viewsaldo;



					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid Voucher Code';
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