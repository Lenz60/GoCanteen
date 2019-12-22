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
            <th>Username</th>
            <th>Password</th>
            <th>Nama</th>
            <th>NIM</th>
            <th>Jurusan</th>
        </tr>
        @foreach ($pembeli as $pem)
        <tr>
            <td>{{ $pem->username }}</td>
            <td>{{ $pem->password }}</td>
            <td>{{ $pem->nama }}</td>
            <td>{{ $pem->nim }}</td>
            <td>{{ $pem->jurusan }}</td>
            <td>
                <a href="/pembeli/edit/edit/{{ $pem->id }}">Edit</a>
                <a href="/pegawai/delete/{{ $pem->id }}">Delete</a>
            </td>
        </tr>
        @endforeach
    </table>
</body>
</html>