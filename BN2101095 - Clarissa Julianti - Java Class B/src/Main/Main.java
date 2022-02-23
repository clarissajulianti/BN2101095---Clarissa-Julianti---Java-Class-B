package Main;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner scan = new Scanner(System.in);
	Vector<Karyawan> listkaryawan = new Vector<Karyawan>();
	public Main() { 
		menu();
	}
		private void menu() {
		while(true) {
		int choose = 0;
		System.out.println("LnT Mid Project");
		System.out.println("===============");
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit");
		System.out.print(">> ");
		try {
			choose = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Input must be numeric!");
		}
		switch (choose) {
		case 1:
			Insertkaryawan(listkaryawan);
			break;
		
		case 2:{
			viewKaryawan(listkaryawan);
			break;
		}
		case 3:{
			UpdateKaryawan(listkaryawan);
			break;
		}
		case 4:{
			DeleteKaryawan(listkaryawan);
			break;
		}
		case 5:{
			System.exit(0);
			break;
	}
			default:
		break;
		 
			}
     	}
	}
		

	public static void Insertkaryawan(Vector<Karyawan> listkaryawan) {
		Scanner scan = new Scanner(System.in);
		String nama="",gender="",jabatan="",kode="";
		do {
			System.out.print("Masukkan nama karyawan [>=3]: ");
			nama=scan.nextLine();
		} while (nama.length()<3);
		
		
		do {
			System.out.print("Masukkan jenis kelamin [Laki-laki | Perempuan] (Case Sensitive):");
			gender=scan.nextLine();
		} while (!(gender.equals("Laki-laki")||gender.equals("Perempuan")));
	
		do {
			System.out.print("Masukkan jabatan [Manager | Supervisor | Admin] (Case Sensitive):");
			jabatan=scan.nextLine();
		} while (!(jabatan.equals("Manager")||jabatan.equals("Supervisor")||jabatan.equals("Admin")));
		
		
		kode = generateId(listkaryawan);
		System.out.println("Berhasil menambahkan karyawan dengan id "+kode);
		bonusKaryawan(listkaryawan, jabatan);
		//adding new karyawan
		listkaryawan.add(new Karyawan(nama, jabatan, kode, gender));
		System.out.println("\nENTER to return\n\n\n");
		scan.nextLine();
		
	}
	
	public static String generateId(Vector<Karyawan> listkaryawan) {
		Random ran= new Random();
		boolean status=false;
		while(true) {
			String temp = (String)(Character.toString((char)(ran.nextInt(26)+'a'))
					+ Character.toString((char)(ran.nextInt(26)+'a'))+'-'
					+ Integer.toString(ran.nextInt(10)) + Integer.toString(ran.nextInt(10))
					+ Integer.toString(ran.nextInt(10))).toUpperCase();
			
			for(Karyawan k:listkaryawan) {
				if(temp.equals(k.GetId())) {
					status=true;
					break;
				}
			}
			if(status) {
				continue;
			}
			else return temp;
		}
		
		
	}
	
	public static void bonusKaryawan(Vector<Karyawan> karyawan, String jabatan) {
		int jumlahKaryawan =0;
		for(Karyawan k:karyawan) {
			if(jabatan.equals(k.GetJabatan())) {
				jumlahKaryawan++;
			}
		}
		if(jumlahKaryawan%3==0) {
			int tempCount=0;
			Vector<String> ids = new Vector<>();
			for(Karyawan k:karyawan) {
				if(k.GetJabatan().equals(jabatan)) {
					Double gajiTemp = k.GetGaji();
					if(jabatan.equals("Manager")) {
						k.SetGaji(gajiTemp*10/100+gajiTemp);
					}
					else if(jabatan.equals("Supervisor")) {
						k.SetGaji(gajiTemp*7.5/100+gajiTemp);
					}
					else if(jabatan.equals("Admin")) {
						k.SetGaji(gajiTemp*5/100+gajiTemp);
					}
					tempCount++;
					ids.add(k.GetId());
					if(tempCount==jumlahKaryawan) {
						if(jabatan.equals("Manager")) {
							System.out.print("Bonus sebesar 10% berhasil ditambahkan kepada karyawan dengan id\n");
							for(String t:ids) {
								System.out.print(t+" ");
							}
						}
						else if(jabatan.equals("Admin")) {
							System.out.print("Bonus sebesar 5% berhasil ditambahkan kepada karyawan dengan id\n");
							int a=0;
							for(String t:ids) {
								System.out.print(t);
								a++;
								if(a<ids.size()){
									System.out.print(", ");
								}
							}
						}
						else if(jabatan.equals("Supervisor")) {
							System.out.print("Bonus sebesar 7.5% berhasil ditambahkan kepada karyawan dengan id\n");
							for(String t:ids) {
								System.out.print(t+" ");
							}
						}
						break;
					}
				}
			}
		}
	}
	public static void viewKaryawan(Vector<Karyawan> listkaryawan) {
		
		if(listkaryawan.size()>0) {
			System.out.println("\n\n");
			//Vector<Karyawan> tempKaryawan = karyawan;
			for (int i = 0; i < listkaryawan.size() - 1; i++) {
	            for (int j = 0; j < listkaryawan.size() - i - 1; j++) {
	                if (listkaryawan.get(j).GetName().compareTo(listkaryawan.get(j+1).GetName()) > 0) {
	                    Karyawan temp= listkaryawan.get(j);
	                    listkaryawan.set(j, listkaryawan.get(j+1));
	                    listkaryawan.set(j+1, temp);
	                 }
	            }
	        }
			//63
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			System.out.printf("\n| %-5s | %-15s | %-35s | %-15s | %-10s | %-15s |%n", "No","Kode Karyawan","Nama Karyawan","Jenis Kelamin","Jabatan","Gaji Karyawan");
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			for(int i=0;i<listkaryawan.size();i++) {
				System.out.printf("\n| %5s | %15s | %35s | %15s | %10s | %15s |%n", 
						Integer.toString(i+1),listkaryawan.get(i).GetId(),listkaryawan.get(i).GetName(),listkaryawan.get(i).Getgender(),
						listkaryawan.get(i).GetJabatan(), Double.toString(listkaryawan.get(i).GetGaji()));
			}
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			System.out.println("\n");
		}
		else {
			System.out.println("\n\n========================");
			System.out.println("\tNo Data");
			System.out.println("========================\n\n");
		}
		
	}
	public static void UpdateKaryawan(Vector<Karyawan> listkaryawan) {
	viewKaryawan(listkaryawan);
	
	if(listkaryawan.size()>0) {
		Scanner scan = new Scanner(System.in);
		System.out.print("\nMasukan nomor urutan karyawan yang ingin diupdate: (must numeric!) " );
		int answer = Integer.parseInt(scan.nextLine());
		if(answer>0&&answer<=listkaryawan.size()) {
			String kode;
			while(true) {
				System.out.print("Input Kode karyawan : ");
				kode = scan.nextLine();
				if(kode.equals("0")) {
					kode=listkaryawan.get(answer-1).GetId();
					break;
				}
				else if(!kode.equals("0")) {
					if(ValidasiId(listkaryawan, kode)) {
						break;
					}
				}
			}
			
			while(true) {
				System.out.print("Input nama karyawan [>=3]: ");
				String nama = scan.nextLine();
				if(!nama.equals("0")) {
					if(nama.length()>=3) {
						listkaryawan.get(answer-1).SetNama(nama);
						break;
					}
				}
				else if(nama.equals("0")) {
					break;
				}
			}
			while(true) {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				String gender = scan.nextLine();
				if(gender.equals("0")) {
					break;
				}
				else if(!gender.equals("0")) {
					if(gender.equals("Laki-laki")||gender.equals("Perempuan")) {
						listkaryawan.get(answer-1).Setgender(gender);
						break;
					}
				}
			}
			while(true) {
				System.out.print("Input jabatan [Manager| Supervisor | Admin] (Case Sensitive): ");
				String jabatan = scan.nextLine();
				if(jabatan.equals("0")) {
					break;
				}
				else if(!jabatan.equals("0")) {
					if(jabatan.equals("Manager")||jabatan.equals("Supervisor")||jabatan.equals("Admin")) {
						listkaryawan.get(answer-1).SetJabatan(jabatan);
						break;
					}
				}
			}
			
			while(true) {
				
				System.out.print("Input Gaji Karyawan: ");
				Double gaji = Double.parseDouble(scan.nextLine());
				if(gaji==0) {
					System.out.println("Berhasil mengupdate karyawan dengan id "+listkaryawan.get(answer-1).GetId());
					listkaryawan.get(answer-1).SetId(kode);
					System.out.println("ENTER to return");
					scan.nextLine();
					break;
				}
				else if(gaji!=0&&gaji>0) {
					listkaryawan.get(answer-1).SetGaji(gaji);
					System.out.println("Berhasil mengupdate karyawan dengan id "+listkaryawan.get(answer-1).GetId());
					listkaryawan.get(answer-1).SetId(kode);
					System.out.println("ENTER to return");
					scan.nextLine();
					break;
				}
			}
		}
	}
}
	public static boolean ValidasiId(Vector<Karyawan> listkaryawan,String kode) {
	kode=kode.toUpperCase();
	if(kode.length()<=6) {
		if(Character.isAlphabetic(kode.charAt(0))) {
			if(Character.isAlphabetic(kode.charAt(1))) {
				if(kode.charAt(2)=='-') {
					for(int i=3;i<6;i++) {
						if(!Character.isDigit(kode.charAt(i))) {
							System.out.println("Format id salah!");
							return false;
						}
					}
				}
				else {
					System.out.println("Format id salah!");
					return false;
				}
			}
		}
	}
	else {
		System.out.println("Kode karyawan terlalu panjang!");
		return false;
	}
	return true;
	
}
	public static void DeleteKaryawan(Vector<Karyawan> listkaryawan) {
		viewKaryawan(listkaryawan);
		if(listkaryawan.size()>0) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Input nomor urutan karyawan yang ingin dihapus : ");
			int answer = Integer.parseInt(scan.nextLine());
			if(answer>0&&answer<=listkaryawan.size()) {
				System.out.println("Karyawan dengan kode "+listkaryawan.get(answer-1).GetId()+" berhasil dihapus.");
				listkaryawan.remove(answer-1);
				System.out.println("ENTER to return");
				scan.nextLine();
			}
		}
	}
	
	public static void main(String[] args) {
		new Main();

	}

}
