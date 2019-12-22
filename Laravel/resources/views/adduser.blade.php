<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add User</title>
</head>
<body>
    <form action="/pembeli/storedata" method="POST">
        {{ csrf_field() }}
        <label for="username">Username</label>
        <input type="text" name="usernameField" id="idusernameField">
        <br>
        <label for="password">Password</label>
        <input type="password" name="passwordField" id="idpasswordField">
        <br>
        <label for="nama">Nama</label>
        <input type="text" name="namaField" id="idnamaField">
        <br>
        <label for="NIM">NIM</label>
        <input type="text" name="nimField" id="idnimField">
        <br>
        <label for="jurusan">Jurusan</label>
        <input type="text" name="jurusanField" placeholder="17IF04" id="idjurusanField">
        <br>
        <input type="submit" value="Add data">

    </form>
</body>
</html>