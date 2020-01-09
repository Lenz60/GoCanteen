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
					
					$stmt = $conn->prepare("SELECT u.id, u.name, u.email, s.saldo FROM user u JOIN saldo s ON u.id = s.id_user WHERE email = ? AND password = ?");
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
						

						// $stmt = $conn->prepare("SELECT * FROM saldo WHERE id_user = ?");
						// $stmt->bind_param("s",$user_id);
						// $stmt->execute();
						// $stmt->store_result();
						// $stmt->bind_result($id,$id_user,$saldo);
						// $stmt->fetch();

						// $viewSaldo = array(
						// 	'id' =>$id,
						// 	'id_user' =>$id_user,
						// 	'saldo' =>$saldo,
						// );
						 

						//check voucher code here
						if($code == 'a'){
							$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo + '1000' WHERE id_user = ?");
							$stmt->bind_param("s",$user_id);
							$stmt->execute();
							$response['error'] = false;
							$response['message'] = 'Saldo added Rp 1000';
						}
						else if($code == '8ba6ee30a5'){
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
						
						//Show saldo amount of current user
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
						$response['error'] = true; 
						$response['message'] = 'Invalid Voucher Code';
					}
				}
			break; 

			case 'product=mieayam':

					//mieayam
					$stmt = $conn->prepare("SELECT * FROM product WHERE id = 1 ");
					$stmt->execute();
					$stmt->store_result();
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $id_shop, $name, $price, $category, $stock, $total_sold);
						$stmt->fetch();
						
							$mieayam = array(
								'id'=>$id, 
								'id_shop'=>$id_shop, 
								'name'=>$name,
								'price'=>$price,
								'category'=>$category,
								'stock'=>$stock,
								'total_sold'=>$total_sold,
							);

						

						$response['error'] = false; 
						$response['message'] = 'Product Sync Success';
						$response['mieayam'] = $mieayam; 
					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid Voucher Code';
					}
			break; 

			case 'product=bakso' :
					//bakso
					$stmt = $conn->prepare("SELECT * FROM product WHERE id = 2 ");
					$stmt->execute();
					$stmt->store_result();
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $id_shop, $name, $price, $category, $stock, $total_sold);
						$stmt->fetch();
						
							$bakso = array(
								'id'=>$id, 
								'id_shop'=>$id_shop, 
								'name'=>$name,
								'price'=>$price,
								'category'=>$category,
								'stock'=>$stock,
								'total_sold'=>$total_sold,
							);

						

						$response['error'] = false; 
						$response['message'] = 'Product Sync Success';
						$response['bakso'] = $bakso; 
					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid Product';
					}
				break;

				case 'product=magelangan' :
						//magelangan
						$stmt = $conn->prepare("SELECT * FROM product WHERE id = 3 ");
						$stmt->execute();
						$stmt->store_result();
						if($stmt->num_rows > 0){
							
							$stmt->bind_result($id, $id_shop, $name, $price, $category, $stock, $total_sold);
							$stmt->fetch();
							
								$magelangan = array(
									'id'=>$id, 
									'id_shop'=>$id_shop, 
									'name'=>$name,
									'price'=>$price,
									'category'=>$category,
									'stock'=>$stock,
									'total_sold'=>$total_sold,
								);

							

							$response['error'] = false; 
							$response['message'] = 'Product Sync Success';
							$response['magelangan'] = $magelangan; 
						}else{
							$response['error'] = false; 
							$response['message'] = 'Invalid Product';
						}
					break;


					case 'order' :
						if(isTheseParametersAvailable(array('id_product','id_user',
						'quantity','place','seat_number','total_price'))){
							$id_product = $_POST['id_product']; 
							$id_user = $_POST['id_user']; 
							$quantity = $_POST['quantity']; 
							$place = $_POST['place']; 
							$seat_number = $_POST['seat_number']; 
							$total_price = $_POST['total_price']; 

							$stmt = $conn->prepare("SELECT * FROM saldo WHERE id_user = ?");
							$stmt->bind_param("s", $id_user);
							$stmt->execute();
							$stmt->store_result();
							$stmt->bind_result($idSaldo, $id_userSaldo, $saldoSaldo);
							$stmt->fetch();

							if($saldoSaldo < $total_price) {
								$response['error'] = true; 
								$response['message'] = 'Saldo tidak cukup';
							}
							else {
								$stmt = $conn->prepare("INSERT INTO foodorder(id_product,
								id_user,quantity,place,seat_number,total_price,status) VALUES(?,?,?,?,?,?,'Menunggu')");
								$stmt->bind_param("ssssss", $id_product, $id_user, $quantity, $place, $seat_number, $total_price);
								$stmt->execute();

								$stmt = $conn->prepare("SELECT * FROM foodorder");
								$stmt->execute();
								$stmt->store_result();
								if($stmt->num_rows > 0){

									$stmt = $conn->prepare("UPDATE saldo SET saldo = saldo - ? WHERE id_user = ?");
									$stmt->bind_param("ss", $total_price, $id_user);
									$stmt->execute();

									$stmt = $conn->prepare("UPDATE product SET stock = stock - ?, total_sold = total_sold + 1 WHERE id = ?");
									$stmt->bind_param("ss", $quantity, $id_product);
									$stmt->execute();
									$stmt = $conn->prepare("SELECT * FROM foodorder");
									$stmt->execute();
									$stmt->store_result();
									
									$stmt->bind_result($id,$id_product, $id_user, $quantity, $place, $seat_number, $total_price, $status);
									$stmt->fetch();
									
										$statusOrder = array(
											'id'=>$id, 
											'id_product'=>$id_product, 
											'id_user'=>$id_user,
											'quantity'=>$quantity,
											'place'=>$place,
											'seat_number'=>$seat_number,
											'total_price'=>$total_price,
											'status'=>$status,
										);
									$response['error'] = false; 
									$response['message'] = 'Order Success';
									$response['statusOrder'] = $statusOrder;
								}else{
									$response['error'] = true; 
									$response['message'] = 'Order Failed';
									$response['statusOrder'] = $statusOrder;
								}			

							}
						}else{
							$response['error'] = true; 
							$response['message'] = 'required parameters are not available'; 
						}
					break;

					case 'order=notification' :
						if(isTheseParametersAvailable(array('id_user'))){
							$id_user = $_POST['id_user']; 

							// "SELECT f.id,u.name as Costumer_name, p.name as Food_name, f.quantity as Quantity, f.total_price as Price, f.status as Food_status 
							// from foodorder f JOIN product p ON f.id_product = p.id JOIN user u ON f.id_user = u.id where f.id_user = ? ";
							
							$stmt = $conn->prepare("SELECT f.id,u.name as name_user , p.name as name_product, f.place, f.seat_number,f.quantity, f.total_price , f.status  
							from foodorder f JOIN product p ON f.id_product = p.id JOIN user u ON f.id_user = u.id where f.id_user = ? ");
							$stmt->bind_param("s",$id_user);
							
							$stmt->execute();
							
							$stmt->store_result();
							
							if($stmt->num_rows > 0){
								
								$stmt->bind_result($id, $name_user, $name_product, $place, $seat_number, $quantity, $total_price, $status);
								$stmt->fetch();
								
									$orderNotification = array(
										'id'=>$id, 
										'name_user'=>$name_user, 
										'name_product'=>$name_product,
										'place'=>$place,
										'seat_number'=>$seat_number,
										'quantity'=>$quantity,
										'total_price'=>$total_price,
										'status'=>$status,
									);
								
								$response['error'] = false; 
								$response['message'] = 'Notification sync'; 
								$response['orderNotification'] = $orderNotification; 
							}else{
								$response['error'] = true; 
								$response['message'] = 'Data Null';
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