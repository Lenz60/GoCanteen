<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Hashing\BcryptHasher;
use Illuminate\Support\Facades\Validator;
use App\Http\Controllers\Controller;
use App\User;
class UserController extends Controller
{
    
    //register new user
    public function create(Request $request){
        //creating validator
        $validator = Validator::make($request->all(), [
            'NIM'=> 'required|unique:users',
            'nama'=> 'required',
            'email'=> 'required|unique:users',
            'password'=> 'required',
            'gender'=> 'required'
        ]);

        //if validator fails 
        if ($validator->fails()) {
            return array(
                'error' => true,
                'message'=> $validator->errors()->all()
            );
        }

        //creating new user
        $user = new User();

        //adding values to user
        $user->NIM = $request->input('NIM');
        $user->nama = $request->input('nama');
        $user->email = $request->input('email');
        $user->password = (new BcryptHasher)->make($request->input('password'));
        $user->gender = $request->input('gender');

        //saving user to database
        $user->save();

        //unsetting the password that it will not be returned
        unset($user->password);

        //returning the registered user
        return array('error' => false, 'user' => $user);
        
    }

    public function login(Request $request){
        $validator = Validator::make($request->all(),[
            'NIM'=> 'required',
            'password'=> 'required'
        ]);

        if ($validator->fails()){
            return array(
                'error' => true,
                'message'=> $validator->errors()->all()
            );
        }

        $user = User::where('NIM', $request->input('NIM'))->first();

        if (count($user)) {
            if (password_verify($request->input('password'), $user->password)) {
                unset($user->password);
                return array('error' => false, 'user' => $user);
            } else {
                return array('error' => true, 'message' => 'Invalid password');
            }
        } else {
            return array('error' => true, 'message' => 'User not exist');
        }
    }
}