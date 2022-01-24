# Word Search Puzzle Solver
## General Information 
Program untuk menemukan solusi dari sebuah word search puzzle menggunakan algoritma brute-force
Program akan memeriksa elemen matriks puzzle secara satu persatu untuk menemukan kata yang dicari
Apabila kata telah ditemukan, maka program akan menyimpannya ke dalam matriks baru dan mencetaknya ke layar pengguna
Program **tidak** menggunakan teknik heuristik untuk mengabaikan proses pencarian

## Setup
Untuk Windows Operating System
1. Buka windows command prompt dan masukkan kode berikut
```
reg add HKEY_CURRENT_USER\Console /v VirtualTerminalLevel /t REG_DWORD /d 0x00000001 /f
```
2. Tutup windows command prompt dan jalankan file batch **run.bat** yang terdapat di folder bin
3. Masukan nama file text yang disimpan di folder test berisi konfigurasi dari permainan. Pastikan nama file ada di folder test
4. Apabila hasil program terlihat aneh dan berisi kode, maka ulangi langkah 1 dan 2
5. Jika ingin menambahkan file baru, isi dari file harus sesuai dengan ketentuan sebagai berikut (Letakkan matriks di bagian atas, beri jarak satu line, dan masukkan tes kasus)
```
J S O L U T I S
S U N A R U U A
N E P T U N E T
S O N I E I S U
R C E V T R E R
A H T R A E S N
M M E R C U R Y

EARTH
MARS
VENUS
MERCURY
SATURN
URANUS
NEPTUNE
JUPITER
```

## Created by:
Yakobus Iryanto Prasethio (13520104)