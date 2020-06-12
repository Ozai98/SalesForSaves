import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.StringTokenizer;


public class upload {

	/**
	 *
	 */
	private static final String Images = "\\Test Images\\";

	public static void main(String[] args) throws Exception {
		//Registering the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException ex) {
			System.out.println("Error al registrar el driver de MySQL: " + ex);
		}
		//DriverManager.registerDriver(new );
		//getting the list of data for user
		String directory = "C:\\Users\\matei\\git\\Ingesoft\\Ingesoft-II\\Back-End\\DB";
		BufferedReader reader = new BufferedReader(new FileReader(directory.concat("\\Usuarios.txt")));
		String linea= reader.readLine();
		StringTokenizer st;


		//Getting the connection
		String mysqlUrl = "jdbc:mysql://sfsback:0000@localhost:3306/savesforsales?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		Connection con = DriverManager.getConnection(mysqlUrl);
		System.out.println("Connection established......");
		String query = "INSERT INTO User(name, mail, password, avatar) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		//Inserting values User
		while(!(linea == null)){
			st = new StringTokenizer(linea);
			pstmt.setString(1, st.nextToken());
			pstmt.setString(2, st.nextToken());
			pstmt.setString(3, st.nextToken());
			pstmt.setBinaryStream(4, new FileInputStream(directory.concat(Images.concat(st.nextToken()))));
			if(pstmt.execute()){
				System.out.println("insertado usuario");
			}
			linea = reader.readLine();
		}
		reader.close();

		//Insert Providers
		reader = new BufferedReader(new FileReader(directory.concat("\\proveedor.txt")));
		linea = reader.readLine();
		query = "INSERT INTO Provider(name, mail, password, avatar, ubication) VALUES (?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(query);
		while(!(linea == null)){
			pstmt.setString(1, linea);
			linea = reader.readLine();
			pstmt.setString(2, linea);
			linea = reader.readLine();
			pstmt.setString(3, linea);
			linea = reader.readLine();
			pstmt.setBinaryStream(4, new FileInputStream(directory.concat(Images.concat(linea))));
			linea = reader.readLine();
			pstmt.setString(5, linea);
			linea = reader.readLine();
		}
		System.out.println("insertados Proveedores");
		reader.close();

		//insert Products
		reader = new BufferedReader(new FileReader(directory.concat("\\productos.txt")));
		linea = reader.readLine();
		query = "INSERT INTO Product(name, price,saved, provider, image, quantity, publicationDate, timeLimit, category) VALUES (?,?,?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query);
		while(!(linea == null)){
			pstmt.setString(1, linea);
			linea = reader.readLine();
			pstmt.setDouble(2, Double.parseDouble(linea));
			linea = reader.readLine();
			pstmt.setDouble(3, Double.parseDouble(linea));
			linea = reader.readLine();
			pstmt.setInt(4, Integer.parseInt(linea));
			linea = reader.readLine();
			pstmt.setBinaryStream(5, new FileInputStream(directory.concat(Images.concat(linea))));
			linea = reader.readLine();
			pstmt.setDouble(6, Double.parseDouble(linea));
			linea = reader.readLine();
			pstmt.setDate(7, Date.valueOf(linea));
			linea = reader.readLine();
			pstmt.setDate(8, Date.valueOf(linea));
			linea = reader.readLine();
			pstmt.setString(9, linea);
			linea = reader.readLine();
		}
		System.out.println("productos insertados");
		System.out.println("Records inserted......");
	}
}