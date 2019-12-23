<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <a href="/pembeli/adduser">+ Add user</a>
    <br>
    <br>
    <table border="1">
        <tr>
            <th>Nim</th>
            <th>Nama</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Password</th>
            <th>Action</th>
        </tr>
        @foreach ($pembeli as $pem)
        <tr>
            <td>{{ $pem->NIM }}</td>
            <td>{{ $pem->nama }}</td>
            <td>{{ $pem->gender }}</td>
            <td>{{ $pem->email }}</td>
            <td>{{ $pem->password }}</td>
            <td>
                <a href="/pembeli/edit/edit/{{ $pem->id }}">Edit</a>
                <a href="/pegawai/delete/{{ $pem->id }}">Delete</a>
            </td>
        </tr>
        @endforeach
    </table>
</body>
</html>