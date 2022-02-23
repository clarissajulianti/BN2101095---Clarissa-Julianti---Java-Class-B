package Main;

public class Karyawan {

		private String gender;
		private Double Gaji;
		private String Id;
		private String Name;
		private String Jabatan;
		
		public Karyawan(String nama, String jabatan,String id, String gender) {
			this.Name=nama;
			this.Jabatan=jabatan;
			if(jabatan.equals("Manager")) {
				this.Gaji=8000000d;
			}
			else if(jabatan.equals("Supervisor")) {
				this.Gaji=6000000d;
			}
			else if(jabatan.equals("Admin")) {
				this.Gaji=4000000d;
			}
			this.Id=id;
			this.gender=gender;
		}
		
		public void SetId(String kode) {
			this.Id=kode;
			
		}

		public void SetJabatan(String jabatan2) {
			this.Jabatan=jabatan2;
		}

		public void Setgender(String gender2) {
			this.gender=gender2;
		}

		public void SetNama(String nama) {
			this.Name=nama;
			
		}

		public void SetGaji(Double g) {
			this.Gaji=g;
		}
		
		public String Getgender() {
			return this.gender;
		}
		
		public Double GetGaji() {
			return this.Gaji;
		}
		
		public String GetJabatan() {
			return this.Jabatan;
		}
		
		public String GetName() {
			return this.Name;
		}
		
		public String GetId() {
			return this.Id;
		}

	
	
		


}
