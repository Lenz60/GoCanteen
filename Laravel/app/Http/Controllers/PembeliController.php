<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PembeliController extends Controller
{
    public function index(){

        //get all data from table pembeli
        $pembeli = DB::table('pembeli')->get();

        //return the data pembeli to view index
        return view('index', ['pembeli' => $pembeli]);
    }

    public function add(){
        //call view add
        return view('adduser');
    }

    public function store(Request $request){
        //insert data to table pembeli
        DB::table('pembeli')->insert([
            'username' => $request->usernameField,
            'password' => $request->passwordField,
            'nama' => $request->namaField,
            'nim' => $request->nimField,
            'jurusan' => $request->jurusanField,
        ]);
        //redirect to pembeli
        return redirect('/pembeli');
    }

    public function login(){
        return view('loginpembeli');
    }
}
