<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/


/*
Route::get('/', function () {
    return view('welcome');
});
*/

Route::get('pembeli/login','PembeliController@login');
Route::get('pembeli/register','ApiController@index');
Route::get('pembeli','PembeliController@index');
Route::get('pembeli/adduser','PembeliController@add');
Route::post('pembeli/storedata','PembeliController@store');


