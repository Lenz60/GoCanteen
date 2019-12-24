<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PembeliController extends Controller
{
    public function index(){
        

        //get all data from table pembeli
        $pembeli = DB::table('users')->get();

        //return the data pembeli to view index
        return view('index', ['pembeli' => $pembeli]);
    }

    public function add(){
        //call view add
        return view('adduser');
    }

    public function store(Request $request){
        //insert data to table pembeli
        DB::table('users')->insert([
            'nim' => $request->NIMField,
            'nama' => $request->namaField,
            'email' => $request->emailField,
            'password' => $request->passwordField,
            'gender' => $request->gender,
        ]);
        //redirect to pembeli
        return redirect('/pembeli');
    }

    public function login(){
        return view('loginpembeli');
    }

}
