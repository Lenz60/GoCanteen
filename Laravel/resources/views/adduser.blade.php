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
        <label for="NIM">NIM</label>
        <input type="text" name="NIMField" id="idnimField">
        <br>
        <label for="nama">Nama</label>
        <input type="text" name="namaField" id="idnamaField">
        <br>
        <label for="email">Email</label>
        <input type="email" name="emailField" id="idemailField">
        <br>
        <label for="password">Password</label>
        <input type="password" name="passwordField" id="idpasswordField">
        <br>
        <label for="gender">Gender</label>
        <fieldset id="gender">
            <input type="radio" name="gender" id="idgenderRadio1" value="Laki-laki" checked>Laki-laki
            <input type="radio" name="gender" id="idgenderRadio2" value="Perempuan">Perempuan
        </fieldset>
        <br>
        <input type="submit" value="Add data">

    </form>
</body>
</html>