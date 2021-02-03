package logica;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

import dominio.Artista;
import dominio.Categoria;
import dominio.Espectaculo;
import dominio.Espectador;
import dominio.Funcion;
import dominio.Paquete;
import dominio.Plataforma;
import dominio.Premio;
import dominio.Registro;
import dominio.Valoracion;
import dtos.ImagenDto;
import excepciones.PlataformaYaExisteException;
import excepciones.UsuarioYaExisteException;

public class CargadorDeDatosDePrueba {

	public CargadorDeDatosDePrueba() throws Exception {
		ManejadorDeUsuarios mUsuarios = ManejadorDeUsuarios.getInstance();
		
		//Espectadores
		Date Efecha1 = new Date(71, 11, 31);
		Date Efecha2 = new Date(83, 10, 15);
		Date Efecha3 = new Date(90, 3, 15);
		Date Efecha4 = new Date(59, 4, 15);
		Date Efecha5 = new Date(50, 0, 28);
		Date Efecha6 = new Date(76, 2, 17);
		Date Efecha7 = new Date(55, 1, 14);
		Date Efecha8 = new Date(27, 1, 23);
		Date Efecha9 = new Date(37, 4, 8);
		
		Espectador esp1 = new Espectador("eleven11", "Eleven", "Ten", "eleven11@gmail.com", Efecha1, "lkj34df");
		Espectador esp2 = new Espectador("costas", "Gerardo", "Costas", "gcostas@gmail.com", Efecha2, "poke579");
		Espectador esp3 = new Espectador("watson", "Emma", "Watson", "e.watson@gmail.com", Efecha3, "mkji648");
		Espectador esp4 = new Espectador("house", "Gregory", "House", "greghouse@gmail.com", Efecha4, "fcku0123");
		Espectador esp5 = new Espectador("segiop", "Sergio", "Puglia", "puglia@alpanpan.com.uy", Efecha5, "vbmn4r");
		Espectador esp6 = new Espectador("chino", "Alvaro", "Recoba", "chino@trico.org.uy", Efecha6, "ncnl123");
		Espectador esp7 = new Espectador("tonyp", "Antonio", "Pacheco", "eltony@manya.org.uy", Efecha7, "mny101");
		Espectador esp8 = new Espectador("lachiqui", "Mirtha", "Legrand", "lachiqui@hotmail.com.ar", Efecha8, "1o1vbm");
		Espectador esp9 = new Espectador("cbochinche", "Cacho", "Bochinche", "cbochinche@vera.com.uy", Efecha9, "ultraton01");
		try {
			mUsuarios.addEspectador(esp1, new ImagenDto());
			mUsuarios.addEspectador(esp2, new ImagenDto());
			mUsuarios.addEspectador(esp3, new ImagenDto());
			mUsuarios.addEspectador(esp4, new ImagenDto());
			mUsuarios.addEspectador(esp5, new ImagenDto());
			mUsuarios.addEspectador(esp6, new ImagenDto());
			mUsuarios.addEspectador(esp7, new ImagenDto());
			mUsuarios.addEspectador(esp8, new ImagenDto());
			mUsuarios.addEspectador(esp9, new ImagenDto());
		} catch (UsuarioYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		esp3.setUrlImagen("https://imgcdn.larepublica.co/i/480/2020/06/16124123/Emma-Watson-bis.jpg");
		esp4.setUrlImagen("https://static.wikia.nocookie.net/dr-house/images/b/bc/House.jpg/revision/latest?cb=20090927231310&path-prefix=es");
		esp5.setUrlImagen("https://imagenes.montevideo.com.uy/imgnoticias/201706/_W933_80/616113.JPG");
		esp6.setUrlImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH4-x-5urKk0qDkboOZtClzoaDBR76wUntZA&usqp=CAU");
		esp7.setUrlImagen("https://www.ztecnos.com/ud/media/k2/items/cache/db4e2e68244d1c1019e9a103611ea9f9_XL.jpg");
		// --------------------------------------------------
		
		//Artistas
			//Descripciones
		String desc1 = "Village People es una innovadora formacion musical\r\n" + 
				"de estilo disco de finales de los anios 70. Fue famosa\r\n" + 
				"tanto por sus peculiares disfraces, como por sus\r\n" + 
				"canciones pegadizas, con letras sugerentes y llenas de\r\n" + 
				"dobles sentidos.";
		
		String desc2 = "Depeche Mode es un grupo ingles de musica\r\n" + 
				"electronica formado en Basildon, Essex, en 1980 por\r\n" + 
				"Vicent Clarke y Andrew John Fletcher, a los que se\r\n" + 
				"unieron Martin Lee Gore y poco despues David Gahan.\r\n" + 
				"Actualmente se le considera como grupo de musica\r\n" + 
				"alternativa.";
		
		String desc3 = "Cynthia Ann Stephanie Lauper, conocida simplemente\r\n" + 
				"como Cyndi Lauper, es una cantautora, actriz y\r\n" + 
				"empresaria estadounidense. Despues de participar en\r\n" + 
				"el grupo musical, Blue Angel, en 1983 firma con\r\n" + 
				"Portrait Records (filial de Epic Records) y lanza su\r\n" + 
				"exitoso Album debut She's So Unusual a finales de ese\r\n" + 
				"mismo anioo. Siguio lanzando una serie de Albumes en\r\n" + 
				"los que encontro una inmensa popularidad, superando\r\n" + 
				"los limites de contenido de las letras de sus canciones.";
		
		String desc4 = "Bruce Frederick Joseph Springsteen (Long Branch,\r\n" + 
				"Nueva Jersey, 23 de septiembre de 1949), mas conocido\r\n" + 
				"como Bruce Springsteen, es un cantante, musico y\r\n" + 
				"compositor estadounidense.";
		
		String desc5 = "La Triple Nelson es un grupo de rock uruguayo\r\n" + 
				"formado en enero de 1998 e integrado inicialmente por\r\n" + 
				"Christian Cary (guitarra y voz), Fernando \"Paco\" Pintos\r\n" + 
				"(bajo y coros) y Ruben Otonello (actualmente su nuevo\r\n" + 
				"baterista es Rafael Ugo).\r\n";
		
		String desc6 = "La Ley fue una banda chilena de rock formada en 1987\r\n" + 
				"por iniciativa del tecladista y guitarrista. En un\r\n" + 
				"principio, La Ley tenia la aspiracion de ser un grupo de\r\n" + 
				"musica tecno. Este disco resulta ser un exito de ventas\r\n" + 
				"y reciben una invitacion al Festival Internacional de\r\n" + 
				"Vinia del Mar de febrero de 1994.";
		
		String desc7 = "Pimpinela es un duo musical argentino compuesto por\r\n" + 
				"los hermanos Luca Galan y Joaquin Galan. Pimpinela\r\n" + 
				"ha editado veinticuatro discos";
		
		String desc8 = "Jose Gomez Romero, conocido artisticamente como\r\n" + 
				"Dyango es un cantante español de musica romantica.";
		
		String desc9 ="Su carrera comienza en 1976 cuando forma la banda\r\n" + 
				"Los Playeros junto a su hermano Victor. Al poco\r\n" + 
				"tiempo se mudan a San Luis donde comienzan a\r\n" + 
				"hacerse conocidos en la escena musical. Su exito a nivel\r\n" + 
				"nacional llega a comienzos de los anios 1990 cuando\r\n" + 
				"desembarca en Buenos Aires y graba el exito \"Violeta\",\r\n" + 
				"originalmente compuesta e interpretada en 1985 por el\r\n" + 
				"musico brasilenio Luiz Caldas bajo el titulo Fricote.\r\n";
			
			//Biografias
		
		String bio1 = "Su carrera comienza en 1976 cuando forma la banda\r\n" + 
				"Los Playeros junto a su hermano Victor. Al poco\r\n" + 
				"tiempo se mudan a San Luis donde comienzan a\r\n" + 
				"hacerse conocidos en la escena musical. Su exito a nivel\r\n" + 
				"nacional llega a comienzos de los anios 1990 cuando\r\n" + 
				"desembarca en Buenos Aires y graba el exito \"Violeta\",\r\n" + 
				"originalmente compuesta e interpretada en 1985 por el\r\n" + 
				"musico brasilenio Luiz Caldas bajo el titulo Fricote.\r\n";
		
		String bio2 ="";
		String bio3 = "Cynthia Ann Stephanie Lauper\r\n" + 
				"(Brooklyn, Nueva York; 22 de junio\r\n" + 
				"de 1953).";
		
		String bio4 ="";
		String bio5 ="";
		String bio6 ="";
		String bio7 ="";
		String bio8 ="";
		String bio9 ="";
		
		Date Afecha1 = new Date(77, 0, 1);
		Date Afecha2 = new Date(80, 5, 14);
		Date Afecha3 = new Date(53, 5, 22);
		Date Afecha4 = new Date(49, 8, 23);
		Date Afecha5 = new Date(98, 0, 1);
		Date Afecha6 = new Date(87, 1, 14);
		Date Afecha7 = new Date(81, 7, 13);
		Date Afecha8 = new Date(40, 2, 5);
		Date Afecha9 = new Date(52, 6, 17);
		
		Artista art1 = new Artista("vpeople", "Village", "People", "vpeople@tuta.io", Afecha1, desc1 , bio1, "www.officialvillagepeople.com", "asdfg456");
		Artista art2 = new Artista("dmode", "Depeche", "Mode", "dmode@tuta.io", Afecha2, desc2, bio2, "www.depechemode.com", "123rtgfdv");
		Artista art3 = new Artista("clauper", "Cyndi", "Lauper", "clauper@hotmail.com", Afecha3, desc3, bio3, "cyndilauper.com", "poiuy086");
		Artista art4 = new Artista("bruceTheBoss", "Bruce", "Springsteen", "bruceTheBoss@gmail.com", Afecha4, desc4, bio4, "brucespringstreen.net", "GTO468");
		Artista art5 = new Artista("tripleNelson", "La Triple", "Nelson", "tripleNelson@tuta.io", Afecha5, desc5, bio5, "www.latriplenelson.uy", "HGF135");
		Artista art6 = new Artista("la_ley", "La", "Ley", "la_ley@tuta.io", Afecha6, desc6, bio6, "www.lasleyesdenewton.com", "lkj65D");
		Artista art7 = new Artista("lospimpi", "Pimpinela", "Pimpinela", "lospimpi@gmail.com", Afecha7, desc7, bio7, "www.pimpinela.net", "jhvf395");
		Artista art8 = new Artista("dyangounchained", "Dyango", "Ango", "dyangounchained@gmail.com", Afecha8, desc8, bio8, "", "ijngr024");
		Artista art9 = new Artista("alcides", "Alcides", "Violeta", "alcides@tuta.io", Afecha9, desc9, bio9, "", "987mnbgh");
		
		try {
			mUsuarios.addArtista(art1);
			mUsuarios.addArtista(art2);
			mUsuarios.addArtista(art3);
			mUsuarios.addArtista(art4);
			mUsuarios.addArtista(art5);
			mUsuarios.addArtista(art6);
			mUsuarios.addArtista(art7);
			mUsuarios.addArtista(art8);
			mUsuarios.addArtista(art9);
		} catch (UsuarioYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		art1.setUrlImagen("https://cadenaser00.epimg.net/ser/imagenes/2019/02/23/radio_cartagena/1550920904_999243_1550921064_noticia_normal_recorte1.jpg");
		art2.setUrlImagen("https://ep01.epimg.net/elpais/imagenes/2017/11/29/tentaciones/1511967608_735359_1511973491_noticia_normal_recorte1.jpg");
		art3.setUrlImagen("https://www.duna.cl/media/2019/06/CyndiLauper-1920x1280.jpg");
		art4.setUrlImagen("https://fotos00.deia.eus/2020/06/19/690x278/bruce-springsteen-a-trump.jpg");
		art5.setUrlImagen("https://imagenes.montevideo.com.uy/imgnoticias/202002/_W933_80/720035.jpg");
		art6.setUrlImagen("https://www.cmtv.com.ar/tapas-cd/laleyretour.jpg");
		art7.setUrlImagen("https://pbs.twimg.com/profile_images/1224543978806267904/G3oSfnj1_400x400.jpg");
		art8.setUrlImagen("https://images-na.ssl-images-amazon.com/images/I/41mL6k60hmL._SX425_.jpg");
		art9.setUrlImagen("https://images.clarin.com/2015/10/16/B1g6SZaCXg_1256x620.jpg");
		
	
		
		// --------------------------------------------------

		//Seguidores
		
		ControladorDeUsuarios cu = new ControladorDeUsuarios();
		
		
		cu.seguir(art1.getNickname(), art4.getNickname());


		cu.seguir(art2.getNickname(), art3.getNickname());
		cu.seguir(art2.getNickname(), art4.getNickname());
		
		cu.seguir(art3.getNickname(), art1.getNickname());
		cu.seguir(art3.getNickname(), art2.getNickname());
		cu.seguir(art3.getNickname(), art8.getNickname());
		
		cu.seguir(art4.getNickname(), art1.getNickname());
		cu.seguir(art4.getNickname(), art2.getNickname());
		cu.seguir(art4.getNickname(), art3.getNickname());
		cu.seguir(art4.getNickname(), esp4.getNickname());
		
		cu.seguir(art5.getNickname(), art3.getNickname());
		cu.seguir(art5.getNickname(), art6.getNickname());
		cu.seguir(art5.getNickname(), esp3.getNickname());
		
		cu.seguir(art6.getNickname(), art2.getNickname());
		cu.seguir(art6.getNickname(), art7.getNickname());
		cu.seguir(art6.getNickname(), esp4.getNickname());
		
		cu.seguir(art7.getNickname(), art2.getNickname());
		cu.seguir(art7.getNickname(), art8.getNickname());
		cu.seguir(art7.getNickname(), art9.getNickname());
		
		cu.seguir(art8.getNickname(), art5.getNickname());
		cu.seguir(art8.getNickname(), art7.getNickname());
		
		cu.seguir(art9.getNickname(), art7.getNickname());
		cu.seguir(art9.getNickname(), esp5.getNickname());
		
		cu.seguir(esp1.getNickname(), art7.getNickname());
		cu.seguir(esp1.getNickname(), art8.getNickname());
		cu.seguir(esp1.getNickname(), esp3.getNickname());
		cu.seguir(esp1.getNickname(), esp6.getNickname());
		cu.seguir(esp1.getNickname(), esp7.getNickname());
		
		cu.seguir(esp2.getNickname(), art1.getNickname());
		cu.seguir(esp2.getNickname(), art2.getNickname());
		cu.seguir(esp2.getNickname(), art3.getNickname());
		cu.seguir(esp2.getNickname(), art4.getNickname());
		cu.seguir(esp2.getNickname(), art5.getNickname());
		cu.seguir(esp2.getNickname(), art6.getNickname());
		cu.seguir(esp2.getNickname(), art7.getNickname());
		cu.seguir(esp2.getNickname(), art8.getNickname());
		cu.seguir(esp2.getNickname(), art9.getNickname());

		cu.seguir(esp3.getNickname(), art2.getNickname());
		cu.seguir(esp3.getNickname(), art3.getNickname());
		cu.seguir(esp3.getNickname(), art4.getNickname());
		cu.seguir(esp3.getNickname(), esp4.getNickname());
		
		cu.seguir(esp4.getNickname(), art4.getNickname());
		cu.seguir(esp4.getNickname(), art6.getNickname());
		cu.seguir(esp4.getNickname(), art8.getNickname());
		
		cu.seguir(esp5.getNickname(), art1.getNickname());
		cu.seguir(esp5.getNickname(), art6.getNickname());
		cu.seguir(esp5.getNickname(), art7.getNickname());
		cu.seguir(esp5.getNickname(), esp6.getNickname());
		cu.seguir(esp5.getNickname(), esp7.getNickname());
		cu.seguir(esp5.getNickname(), esp8.getNickname());
		
		cu.seguir(esp6.getNickname(), art9.getNickname());
		cu.seguir(esp6.getNickname(), esp5.getNickname());
		
		cu.seguir(esp7.getNickname(), art9.getNickname());
		cu.seguir(esp7.getNickname(), esp5.getNickname());
		
		cu.seguir(esp8.getNickname(), art7.getNickname());
		cu.seguir(esp8.getNickname(), art9.getNickname());
		
		cu.seguir(esp9.getNickname(), art6.getNickname());
		cu.seguir(esp9.getNickname(), art7.getNickname());
		cu.seguir(esp9.getNickname(), art9.getNickname());
		cu.seguir(esp9.getNickname(), esp6.getNickname());
		cu.seguir(esp9.getNickname(), esp7.getNickname());
		cu.seguir(esp9.getNickname(), esp8.getNickname());
		// --------------------------------------------------
		
		//PLataformas
			//Descripciones
		
				
		ManejadorDePlataformas manejadorP = ManejadorDePlataformas.getInstance();
		
		String pd1 =  "Funcionalidad de la red social Instagram, con la que\r\n" + 
				"los usuarios pueden transmitir videos en vivo.";
		
		String pd2 = "Servicio de video bajo demanda operado por\r\n" + 
				"Facebook."; 
		
		String pd3 = "Aplicacion de Twitter para la transmision de video\r\n" + 
				"en directo (streaming).";
				
		String pd4 = "Sitio web de origen estadounidense dedicado a\r\n" + 
				"compartir videos.";
		
		Plataforma plat1 = new Plataforma("Instagram Live", pd1, "https://www.instagram.com/liveoficial");
		Plataforma plat2 = new Plataforma("Facebook Watch", pd2, "https://www.facebook.com/watch/");
		Plataforma plat3 = new Plataforma("Twitter Live", pd3, "https://twitter.com/");
		Plataforma plat4 = new Plataforma("Youtube", pd4, "https://www.youtube.com/");
		
		try {
			manejadorP.addPlataforma(plat1);
			manejadorP.addPlataforma(plat2);
			manejadorP.addPlataforma(plat3);
			manejadorP.addPlataforma(plat4);
		} catch (PlataformaYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ----------------------------------------------------
		
		//Espectaculos
		
		Date Espfecha1 = new Date(120, 2, 31);
		Date Espfecha2 = new Date(120, 3, 20);
		Date Espfecha3 = new Date(120, 4, 30);
		Date Espfecha4 = new Date(120, 5, 7);
		Date Espfecha5 = new Date(120, 6, 8);
		Date Espfecha6 = new Date(120, 6, 31);
		Date Espfecha7 = new Date(120, 0, 9);
		Date Espfecha8 = new Date(120, 4, 20);
		Date Espfecha9 = new Date(120, 10, 25);
		Date Espfecha10 = new Date(120, 10, 25);
		Date EspFechaFin3 = new Date(120, 10, 25);
		Date EspFechaFin6 = new Date(120, 10, 26);
		
		Espectaculo Esp1 = new Espectaculo(plat1, art1, "Los Village Volvieron", "Espectaculo de retorno de los Village People.", 1, 30,
				1, 800, "https://www.instagram.com/realvillagepeople", 550, Espfecha1, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp2 = new Espectaculo(plat2, art2, "Global Spirit", "Espectaculo donde se presenta el Album Spirit.", 2, 0,
				1, 1300, "https://es-la.www.facebook.com/depechemode/", 750, Espfecha2, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp3 = new Espectaculo(plat3, art3, "Memphis Blues World", "Espectaculo promoviendo Album Memphis Blues.", 1, 50,
				1, 1000, "https://twitter.com/cyndilauper", 800, Espfecha3, Espectaculo.Estado.FINALIZADO);
		
		Espectaculo Esp4 = new Espectaculo(plat4, art4, "Springsteen on Broadway", "Springsteen tocando guitarra piano y relatando anecdotas recogidas en su autobiografia de 2016, Born to Run.", 1, 40,
				1, 1500, "https://www.youtube.com/BruceSpringsteen", 980, Espfecha4, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp5 = new Espectaculo(plat3, art7, "Bien de Familia", "El duo estara presentando sus mas sonados exitos y tambien nuevas canciones.", 2, 30,
				1, 500, "https://twitter.com/PimpinelaNet", 500, Espfecha5, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp6 = new Espectaculo(plat3, art9, "30 anios", "Espectaculo conmemorando los 30 anios de Violeta.", 1, 20,
				3, 150, "https://twitter.com/alcides_shows", 450, Espfecha6, Espectaculo.Estado.FINALIZADO);
		
		Espectaculo Esp7 = new Espectaculo(plat4, art8, "Grandes Exitos 2020", "Espectaculo de gira con los temas de siempre", 2, 0,
				3, 4, "https://www.youtube.com/c/dyangooficial", 550, Espfecha7, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp8 = new Espectaculo(plat1, art5, "Llega a casa", "Primer Espectaculo con transmicion por streaming.", 1, 40,
				100, 1500, "https://www.instagram.com/latriplenelson/", 400, Espfecha8, Espectaculo.Estado.ACEPTADO);
		
		Espectaculo Esp9 = new Espectaculo(plat3, art9, "Noche buena con Alcides y amigos", "Esta nochebuena, festejamos con Alcides y grandes invitados", 1, 0,
				1, 3, "https://twitter.com/alcides_shows", 600, Espfecha9, Espectaculo.Estado.INGRESADO);
		
		Espectaculo Esp10 = new Espectaculo(plat3, art9, "Fin de anio con Alcides y amigos", "Esta fin de anio, festejamos con Alcides y grandes invitados", 1, 0,
				1, 3, "https://twitter.com/alcides_shows", 700, Espfecha10, Espectaculo.Estado.RECHAZADO);
		
		Esp1.setUrlVideo("https://www.youtube.com/watch?v=N8FxU1nmLWg&feature=youtu.be");
		Esp2.setUrlVideo("https://www.youtube.com/watch?v=2qxcr6T9pNM&feature=youtu.be");
		Esp3.setUrlVideo("https://www.youtube.com/watch?v=ivHp3_gYXIc&feature=youtu.be");
		Esp4.setUrlVideo("https://www.youtube.com/watch?v=M1xDzgob1JI&feature=youtu.be");
		Esp5.setUrlVideo("https://www.youtube.com/watch?v=dPSlBRg0HeA&feature=youtu.be");
		Esp6.setUrlVideo("https://www.youtube.com/watch?v=65Pu6WP0bag&feature=youtu.be");
		Esp7.setUrlVideo("https://www.youtube.com/watch?v=NxFeibjFt3k&feature=youtu.be");
		Esp8.setUrlVideo("https://www.youtube.com/watch?v=m7r3YIFRI3k&feature=youtu.be");
		Esp9.setUrlVideo("https://www.youtube.com/watch?v=65Pu6WP0bag&feature=youtu.be");
		Esp10.setUrlVideo("https://www.youtube.com/watch?v=65Pu6WP0bag&feature=youtu.be");
		
		Esp3.setFechaFinalizado(EspFechaFin3);
		Esp6.setFechaFinalizado(EspFechaFin6);
		
		plat1.addEspectaculo(Esp1, new ImagenDto());
		plat1.addEspectaculo(Esp8, new ImagenDto());
		plat2.addEspectaculo(Esp2, new ImagenDto());
		plat3.addEspectaculo(Esp3, new ImagenDto());
		plat3.addEspectaculo(Esp5, new ImagenDto());
		plat3.addEspectaculo(Esp6, new ImagenDto());
		plat3.addEspectaculo(Esp9, new ImagenDto());
		plat3.addEspectaculo(Esp10, new ImagenDto());
		plat4.addEspectaculo(Esp4, new ImagenDto());
		plat4.addEspectaculo(Esp7, new ImagenDto());
		
		art1.addEspectaculo(Esp1);
		art2.addEspectaculo(Esp2);
		art3.addEspectaculo(Esp3);
		art4.addEspectaculo(Esp4);
		art5.addEspectaculo(Esp8);
		art7.addEspectaculo(Esp5);
		art8.addEspectaculo(Esp7);
		art9.addEspectaculo(Esp6);
		art9.addEspectaculo(Esp9);
		art9.addEspectaculo(Esp10);
		
		// Premios (Nuevo)
		
		Esp1.setPremio("Meet and greet (encuentro) virtual con integrantes de Village People y un " + 
				"accesorio de indumentaria de la banda que ser elegido por el ganador, " + 
				"como ser el penacho de plumas del jefe indio (sujeto a disponibilidad). " + 
				"Info: https://bit.ly/sorteovp");
	 
		Esp2.setPremio("Box Set multimedia Depeche Mode: SPIRITS in the Forest , que " + 
				"sigue a la banda en su Global Spirit Tour 2017/2018, que vio a " + 
				"Depeche Mode tocar para ms de 3 millones de fanticos en 115 " + 
				"shows en todo el mundo. El Box Set contiene 2 CDs y 2 DVDs o 2 " + 
				"CDs y 1 Blu-ray (a eleccin). Info: https://bit.ly/sorteodm");
	
		Esp3.setPremio("Meet and greet (encuentro) virtual con la legendaria cantante e cono del " + 
				"Pop, que inspir a tantas otras cantante femeninas como Madonna y " + 
				"Lady Gaga (aunque ellas jams lo admitiran).");
	
		Esp4.setPremio("Album completo Springsteen On Broadway en formato MP3 o CD (a " + 
				"eleccin). Info: https://bit.ly/sorteobs");
	
		Esp5.setPremio("Es cierto que son hermanos? La voz de Luca puede romper una copa " + 
				"de cristal? Joaqun quiere dejar Pimpinela y ser el vocalista de una " + 
				"banda de heavy metal? Todas estas preguntas y muchas ms podrs " + 
				"hacrselas a tus dolos en un encuentro on-line exclusivo para los " + 
				"ganadores de este sorteo");
	
		Esp6.setPremio("Entrada en platea VIP para el primer show presencial que realice " + 
				"Alcides a partir de enero de 2021 (una vez que el artista haya recibido " + 
				"la vacuna contra el SARS-COV-2), ms 1 litro de Fernet de marca a " + 
				"confirmar." + 
				"");
	
		Esp7.setPremio("Album completo Y Ahora Que para descargar en formato FLAC (24 " + 
				"bits, 44.1 kHz). Info: https://bit.ly/sorteody");
	
		Esp8.setPremio("Entrada doble para espectculo Mi Bien a realizarse en el Auditorio " + 
				"Nacional del SODRE.");

		
		
		Esp1.setCantPremio(2);
		Esp2.setCantPremio(3);
		Esp3.setCantPremio(2);
		Esp4.setCantPremio(2);
		Esp5.setCantPremio(1);
		Esp6.setCantPremio(3);
		Esp7.setCantPremio(2);
		Esp8.setCantPremio(2);
		
		// -----------------------------------------------------------
		
		//Funciones
		
			//Artistas invitados
		
		LinkedList<Artista> lista1 = new LinkedList<Artista>();
		//Prueba
		lista1.add(art2);
		lista1.add(art3);
		
		LinkedList<Artista> lista2 = new LinkedList<Artista>();
		lista2.add(art4);
	
		LinkedList<Artista> lista3 = new LinkedList<Artista>();
		lista3.add(art4);
		lista3.add(art3);
		
		LinkedList<Artista> lista4 = new LinkedList<Artista>();
		lista4.add(art1);
		
		LinkedList<Artista> lista5 = new LinkedList<Artista>();
		lista5.add(art3);
		lista5.add(art4);
		
		LinkedList<Artista> lista6 = new LinkedList<Artista>();
		lista6.add(art7);
		
		LinkedList<Artista> lista7 = new LinkedList<Artista>();
		lista7.add(art4);
		
		LinkedList<Artista> lista8 = new LinkedList<Artista>();
		lista8.add(art4);
		lista8.add(art2);
		
		LinkedList<Artista> lista9 = new LinkedList<Artista>();
		lista9.add(art7);
		lista9.add(art4);
		
		LinkedList<Artista> lista10 = new LinkedList<Artista>();
		lista10.add(art2);
		lista10.add(art5);
		
		LinkedList<Artista> lista11 = new LinkedList<Artista>();
		lista11.add(art5);
		lista11.add(art6);
		
		LinkedList<Artista> lista12 = new LinkedList<Artista>();
		lista12.add(art6);
		
		LinkedList<Artista> lista13 = new LinkedList<Artista>();
		lista13.add(art9);
		
		LinkedList<Artista> lista14 = new LinkedList<Artista>();
		lista14.add(art5);
		
		LinkedList<Artista> lista15 = new LinkedList<Artista>();
		
		LinkedList<Artista> lista16 = new LinkedList<Artista>();
		lista16.add(art8);
		
		LinkedList<Artista> lista17= new LinkedList<Artista>();
		lista17.add(art7);
		lista17.add(art8);
		
		LinkedList<Artista> lista19= new LinkedList<Artista>();
		lista19.add(art7);
		
		LinkedList<Artista> lista20= new LinkedList<Artista>();
		lista20.add(art7);
		
		Date FFechaIni1 = new Date(120, 3, 15);
		Date FFechaAlta1 = new Date(120, 2, 31);
		
		Date FFechaIni2 = new Date(120, 4, 1);
		Date FFechaAlta2 = new Date(120, 2, 31);
		
		Date FFechaIni3 = new Date(120, 5, 1);
		Date FFechaAlta3 = new Date(120, 2, 31);
		
		Date FFechaIni4 = new Date(120, 5, 10);
		Date FFechaAlta4 = new Date(120, 3, 20);
		
		Date FFechaIni5 = new Date(120, 6, 10);
		Date FFechaAlta5 = new Date(120, 3, 20);
		
		Date FFechaIni6 = new Date(120, 7, 10);
		Date FFechaAlta6 = new Date(120, 3, 20);
		
		Date FFechaIni7 = new Date(120, 7, 15);
		Date FFechaAlta7 = new Date(120, 4, 30);
		
		Date FFechaIni8 = new Date(120, 7, 31);
		Date FFechaAlta8 = new Date(120, 4, 30);
		
		Date FFechaIni9 = new Date(120, 8, 30);
		Date FFechaAlta9 = new Date(120, 4, 30);
		
		Date FFechaIni10 = new Date(120, 8, 1);
		Date FFechaAlta10 = new Date(120, 5, 7);
		
		Date FFechaIni11 = new Date(120, 8, 30);
		Date FFechaAlta11 = new Date(120, 5, 7);
		
		Date FFechaIni12 = new Date(120, 9, 15);
		Date FFechaAlta12 = new Date(120, 5, 7);
		
		Date FFechaIni13 = new Date(120, 8, 25);
		Date FFechaAlta13 = new Date(120, 6, 8);
		
		Date FFechaIni14 = new Date(120, 9, 25);
		Date FFechaAlta14 = new Date(120, 6, 8);
		
		Date FFechaIni15 = new Date(120, 10, 25);
		Date FFechaAlta15 = new Date(120, 6, 8);
		
		Date FFechaIni16 = new Date(120, 8, 1);
		Date FFechaAlta16 = new Date(120, 6, 31);
		
		Date FFechaIni17 = new Date(120, 9, 1);
		Date FFechaAlta17 = new Date(120, 6, 31);
		
		Date FFechaIni18 = new Date(120, 10, 15);
		Date FFechaAlta18 = new Date(120, 6, 31);
		
		Date FFechaIni19 = new Date(120, 11, 19);
		Date FFechaAlta19 = new Date(120, 10, 25);
		
		Date FFechaIni20 = new Date(120, 11, 19);
		Date FFechaAlta20 = new Date(120, 10, 25);
		
		Date FFechaIni21 = new Date(120, 11, 18);
		Date FFechaAlta21 = new Date(120, 10, 24);
		
		Date FFechaIni22 = new Date(120, 11, 19);
		Date FFechaAlta22 = new Date(120, 10, 24);
		
		
		Funcion func1 = new Funcion(lista1, Esp1, "Los Village Volvieron - 1", FFechaIni1, 15, 30, FFechaAlta1);
		Esp1.addFuncion(func1);
		
		Funcion func2 = new Funcion(lista2, Esp1, "Los Village Volvieron - 2", FFechaIni2, 17, 0, FFechaAlta2);
		Esp1.addFuncion(func2);
		
		Funcion func3 = new Funcion(lista3, Esp1, "Los Village Volvieron - 3", FFechaIni3, 18, 0, FFechaAlta3);
		Esp1.addFuncion(func3);
		
		Funcion func4 = new Funcion(lista4, Esp2, "Global Spirit (I)", FFechaIni4, 19, 0, FFechaAlta4);
		Esp2.addFuncion(func4);
		
		Funcion func5 = new Funcion(lista5, Esp2, "Global Spirit (II)", FFechaIni5, 20, 0, FFechaAlta5);
		Esp2.addFuncion(func5);
		
		Funcion func6 = new Funcion(lista6, Esp2, "Global Spirit (III)", FFechaIni6, 17, 45, FFechaAlta6);
		Esp2.addFuncion(func6);
		
		Funcion func7 = new Funcion(lista7, Esp3, "Memphis Blues World - A", FFechaIni7, 16, 30, FFechaAlta7);
		Esp3.addFuncion(func7);
		
		Funcion func8 = new Funcion(lista8, Esp3, "Memphis Blues World - B", FFechaIni8, 19, 30, FFechaAlta8);
		Esp3.addFuncion(func8);
		
		Funcion func9 = new Funcion(lista9, Esp3, "Memphis Blues World - C", FFechaIni9, 20, 0, FFechaAlta9);
		Esp3.addFuncion(func9);
		
		Funcion func10 = new Funcion(lista10, Esp4, "Springsteen on Broadway - i", FFechaIni10, 19, 30, FFechaAlta10);
		Esp4.addFuncion(func10);
		
		Funcion func11 = new Funcion(lista11, Esp4, "Springsteen on Broadway - ii", FFechaIni11, 17, 0, FFechaAlta11);
		Esp4.addFuncion(func11);
		
		Funcion func12 = new Funcion(lista12, Esp4, "Springsteen on Broadway - iii", FFechaIni12, 20, 0, FFechaAlta12);
		Esp4.addFuncion(func12);
		
		Funcion func13 = new Funcion(lista13, Esp5, "Bien de Familia - A", FFechaIni13, 19, 30, FFechaAlta13);
		Esp5.addFuncion(func13);
		
		Funcion func14 = new Funcion(lista14, Esp5, "Bien de Familia - B", FFechaIni14, 18, 30, FFechaAlta14);
		Esp5.addFuncion(func14);
		
		Funcion func15 = new Funcion(lista15, Esp5, "Bien de Familia - C", FFechaIni15, 17, 45, FFechaAlta15);
		Esp5.addFuncion(func15);
		
		Funcion func16 = new Funcion(lista16, Esp6, "30 anios - 1", FFechaIni16, 21, 0, FFechaAlta16);
		Esp6.addFuncion(func16);
		
		Funcion func17 = new Funcion(lista17, Esp6, "30 anios - 2", FFechaIni17, 21, 0, FFechaAlta17);
		Esp6.addFuncion(func17);
		
		Funcion func18 = new Funcion(new LinkedList<Artista>(), Esp6, "30 anios - 3", FFechaIni18, 21, 0, FFechaAlta18);
		Esp6.addFuncion(func18);
		
		//Nuevas funciones
		
		Funcion func19 = new Funcion(lista19, Esp7, "Grandes Exitos 2020 - Dia", FFechaIni19, 17, 0, FFechaAlta19);
		Esp7.addFuncion(func19);
		
		Funcion func20 = new Funcion(lista20, Esp7, "Grandes Exitos 2020 - Noche", FFechaIni20, 21, 0, FFechaAlta20);
		Esp7.addFuncion(func20);
		
		Funcion func21 = new Funcion(new LinkedList<Artista>(), Esp8, "Llega a Casa - 1", FFechaIni21, 21, 30, FFechaAlta21);
		Esp8.addFuncion(func21);
		
		Funcion func22 = new Funcion(new LinkedList<Artista>(), Esp8, "Llega a Casa - 2", FFechaIni22, 21, 30, FFechaAlta22);
		Esp8.addFuncion(func22);
		
		// -------------------------------------------------------
		
		//Registro a Funciones
		
		Registro reg1 = new Registro(func1, esp2, null, 550, true, null, func1.getTopeIdRegistro());
		Date fechaReg1 = new Date(120, 3, 9);
		reg1.setFecha(fechaReg1);
		func1.agregarRegistro(reg1);
		esp2.agregarRegistro(reg1);
		
		Registro reg2 = new Registro(func1, esp5, null, 550, true, null, func1.getTopeIdRegistro());
		Date fechaReg2 = new Date(120, 3, 10);
		reg2.setFecha(fechaReg2);
		func1.agregarRegistro(reg2);
		esp5.agregarRegistro(reg2);
		
		Registro reg3 = new Registro(func1, esp6, null, 550, true, null, func1.getTopeIdRegistro());
		Date fechaReg3 = new Date(120, 3, 12);
		reg3.setFecha(fechaReg3);
		func1.agregarRegistro(reg3);
		esp6.agregarRegistro(reg3);
		
		Registro reg4 = new Registro(func2, esp6, null, 550, true, null, func2.getTopeIdRegistro());
		Date fechaReg4 = new Date(120, 3, 15);
		reg4.setFecha(fechaReg4);
		func2.agregarRegistro(reg4);
		esp6.agregarRegistro(reg4);
		
		Registro reg5 = new Registro(func2, esp7, null, 550, true, null, func2.getTopeIdRegistro());
		Date fechaReg5 = new Date(120, 3, 20);
		reg5.setFecha(fechaReg5);
		func2.agregarRegistro(reg5);
		esp7.agregarRegistro(reg5);
		
		Registro reg6 = new Registro(func2, esp2, null, 550, true, null, func2.getTopeIdRegistro());
		Date fechaReg6 = new Date(120, 3, 25);
		reg6.setFecha(fechaReg6);
		func2.agregarRegistro(reg6);
		esp2.agregarRegistro(reg6);
		
		Registro reg7 = new Registro(func2, esp8, null, 550, true, null, func2.getTopeIdRegistro());
		Date fechaReg7 = new Date(120, 3, 28);
		reg7.setFecha(fechaReg7);
		func2.agregarRegistro(reg7);
		esp8.agregarRegistro(reg7);
		
		Registro reg8 = new Registro(func3, esp9, null, 550, true, null, func3.getTopeIdRegistro());
		Date fechaReg8 = new Date(120, 3, 16);
		reg8.setFecha(fechaReg8);
		func3.agregarRegistro(reg8);
		esp9.agregarRegistro(reg8);
		
		Registro reg9 = new Registro(func3, esp2, null, 550, true, null, func3.getTopeIdRegistro());
		Date fechaReg9 = new Date(120, 4, 15);
		reg9.setFecha(fechaReg9);
		func3.agregarRegistro(reg9);
		esp2.agregarRegistro(reg9);
		
		Registro reg10 = new Registro(func3, esp8, null, 550, true, null, func3.getTopeIdRegistro());
		Date fechaReg10 = new Date(120, 4, 20);
		reg10.setFecha(fechaReg10);
		func3.agregarRegistro(reg10);
		esp8.agregarRegistro(reg10);
		
		Registro reg11 = new Registro(func4, esp1, null, 750, true, null, func4.getTopeIdRegistro());
		Date fechaReg11 = new Date(120, 4, 5);
		reg11.setFecha(fechaReg11);
		func4.agregarRegistro(reg11);
		esp1.agregarRegistro(reg11);
		
		Registro reg12 = new Registro(func4, esp3, null, 750, true, null, func4.getTopeIdRegistro());
		Date fechaReg12 = new Date(120, 4, 10);
		reg12.setFecha(fechaReg12);
		func4.agregarRegistro(reg12);
		esp3.agregarRegistro(reg12);
		
		Registro reg13 = new Registro(func4, esp5, null, 750, true, null, func4.getTopeIdRegistro());
		Date fechaReg13 = new Date(120, 4, 15);
		reg13.setFecha(fechaReg13);
		func4.agregarRegistro(reg13);
		esp5.agregarRegistro(reg13);
		
		Registro reg14 = new Registro(func4, esp7, null, 750, true, null, func4.getTopeIdRegistro());
		Date fechaReg14 = new Date(120, 4, 20);
		reg14.setFecha(fechaReg14);
		func4.agregarRegistro(reg14);
		esp7.agregarRegistro(reg14);
		
		Registro reg15 = new Registro(func5, esp4, null, 750, true, null, func5.getTopeIdRegistro());
		Date fechaReg15 = new Date(120, 5, 8);
		reg15.setFecha(fechaReg15);
		func5.agregarRegistro(reg15);
		esp4.agregarRegistro(reg15);
		
		Registro reg16 = new Registro(func5, esp3, null, 750, true, null, func5.getTopeIdRegistro());
		Date fechaReg16 = new Date(120, 5, 13);
		reg16.setFecha(fechaReg16);
		func5.agregarRegistro(reg16);
		esp3.agregarRegistro(reg16);
		
		Registro reg17 = new Registro(func5, esp8, null, 750, true, null, func5.getTopeIdRegistro());
		Date fechaReg17 = new Date(120, 5, 25);
		reg17.setFecha(fechaReg17);
		func5.agregarRegistro(reg17);
		esp8.agregarRegistro(reg17);
		
		Registro reg18 = new Registro(func6, esp9, null, 750, true, null, func6.getTopeIdRegistro());
		Date fechaReg18 = new Date(120, 6, 5);
		reg18.setFecha(fechaReg18);
		func6.agregarRegistro(reg18);
		esp9.agregarRegistro(reg18);
		
		Registro reg19 = new Registro(func6, esp5, null, 750, true, null, func6.getTopeIdRegistro());
		Date fechaReg19 = new Date(120, 6, 11);
		reg19.setFecha(fechaReg19);
		func6.agregarRegistro(reg19);
		esp5.agregarRegistro(reg19);
		
		Registro reg20 = new Registro(func6, esp6, null, 750, true, null, func6.getTopeIdRegistro());
		Date fechaReg20 = new Date(120, 6, 18);
		reg20.setFecha(fechaReg20);
		func6.agregarRegistro(reg20);
		esp6.agregarRegistro(reg20);
		
		LinkedList<Registro> lista21 = new LinkedList<Registro>();
		lista21.add(reg7);
		lista21.add(reg10);
		lista21.add(reg17);
		
		Registro reg21 = new Registro(func7, esp8, null, 0, false, lista21, func7.getTopeIdRegistro());
		Date fechaReg21 = new Date(120, 6, 19);
		reg21.setFecha(fechaReg21);
		func7.agregarRegistro(reg21);
		esp8.agregarRegistro(reg21);
		
		Registro reg22 = new Registro(func8, esp1, null, 800, true, null, func8.getTopeIdRegistro());
		Date fechaReg22 = new Date(120, 7, 17);
		reg22.setFecha(fechaReg22);
		func8.agregarRegistro(reg22);
		esp1.agregarRegistro(reg22);
		
		Registro reg23 = new Registro(func8, esp4, null, 800, true, null, func8.getTopeIdRegistro());
		Date fechaReg23 = new Date(120, 7, 20);
		reg23.setFecha(fechaReg23);
		func8.agregarRegistro(reg23);
		esp4.agregarRegistro(reg23);
		
		Registro reg24 = new Registro(func8, esp6, null, 800, true, null, func8.getTopeIdRegistro());
		Date fechaReg24 = new Date(120, 7, 23);
		reg24.setFecha(fechaReg24);
		func8.agregarRegistro(reg24);
		esp6.agregarRegistro(reg24);
		
		LinkedList<Registro> lista25 = new LinkedList<Registro>();
		lista25.add(reg1);
		lista25.add(reg6);
		lista25.add(reg9);
		
		Registro reg25 = new Registro(func9, esp2, null, 0, false, lista25, func9.getTopeIdRegistro());
		Date fechaReg25 = new Date(120, 7, 15);
		reg25.setFecha(fechaReg25);
		func9.agregarRegistro(reg25);
		esp2.agregarRegistro(reg25);
		
		Registro reg26 = new Registro(func9, esp3, null, 800, true, null, func9.getTopeIdRegistro());
		Date fechaReg26 = new Date(120, 7, 26);
		reg26.setFecha(fechaReg26);
		func9.agregarRegistro(reg26);
		esp3.agregarRegistro(reg26);
		
		LinkedList<Registro> lista27= new LinkedList<Registro>();
		lista27.add(reg3);
		lista27.add(reg4);
		lista27.add(reg20);
		
		Registro reg27 = new Registro(func10, esp6, null, 0, false, lista27, func10.getTopeIdRegistro());
		Date fechaReg27 = new Date(120, 6, 19);
		reg27.setFecha(fechaReg27);
		func10.agregarRegistro(reg27);
		esp6.agregarRegistro(reg27);
		
		Registro reg28 = new Registro(func10, esp7, null, 980, true, null, func10.getTopeIdRegistro());
		Date fechaReg28 = new Date(120, 7, 16);
		reg28.setFecha(fechaReg28);
		func10.agregarRegistro(reg28);
		esp7.agregarRegistro(reg28);
		
		Registro reg29 = new Registro(func10, esp8, null, 980, true, null, func10.getTopeIdRegistro());
		Date fechaReg29 = new Date(120, 7, 24);
		reg29.setFecha(fechaReg29);
		func10.agregarRegistro(reg29);
		esp8.agregarRegistro(reg29);
		
		LinkedList<Registro> lista30= new LinkedList<Registro>();
		lista30.add(reg2);
		lista30.add(reg13);
		lista30.add(reg19);
		
		Registro reg30 = new Registro(func11, esp5, null, 0, false, lista30, func11.getTopeIdRegistro());
		Date fechaReg30 = new Date(120, 7, 1);
		reg30.setFecha(fechaReg30);
		func11.agregarRegistro(reg30);
		esp5.agregarRegistro(reg30);
		
		Registro reg31 = new Registro(func11, esp4, null, 980, true, null, func11.getTopeIdRegistro());
		Date fechaReg31 = new Date(120, 7, 30);
		reg31.setFecha(fechaReg31);
		func11.agregarRegistro(reg31);
		esp4.agregarRegistro(reg31);
		
		Registro reg32 = new Registro(func12, esp1, null, 980, true, null, func12.getTopeIdRegistro());
		Date fechaReg32 = new Date(120, 7, 16);
		reg32.setFecha(fechaReg32);
		func12.agregarRegistro(reg32);
		esp1.agregarRegistro(reg32);
		
		Registro reg33 = new Registro(func12, esp2, null, 980, true, null, func12.getTopeIdRegistro());
		Date fechaReg33 = new Date(120, 7, 16);
		reg33.setFecha(fechaReg33);
		func12.agregarRegistro(reg33);
		esp2.agregarRegistro(reg33);
		
		Registro reg34 = new Registro(func12, esp3, null, 980, true, null, func12.getTopeIdRegistro());
		Date fechaReg34 = new Date(120, 8, 1);
		reg34.setFecha(fechaReg34);
		func12.agregarRegistro(reg34);
		esp3.agregarRegistro(reg34);
		
		Registro reg35 = new Registro(func12, esp5, null, 980, true, null, func12.getTopeIdRegistro());
		Date fechaReg35 = new Date(120, 8, 5);
		reg35.setFecha(fechaReg35);
		func12.agregarRegistro(reg35);
		esp5.agregarRegistro(reg35);
		
		Registro reg36 = new Registro(func13, esp4, null, 500, true, null, func13.getTopeIdRegistro());
		Date fechaReg36 = new Date(120, 7, 16);
		reg36.setFecha(fechaReg36);
		func13.agregarRegistro(reg36);
		esp4.agregarRegistro(reg36);
		
		Registro reg37 = new Registro(func13, esp9, null, 500, true, null, func13.getTopeIdRegistro());
		Date fechaReg37 = new Date(120, 8, 3);
		reg37.setFecha(fechaReg37);
		func13.agregarRegistro(reg37);
		esp9.agregarRegistro(reg37);
		
		Registro reg38 = new Registro(func14, esp1, null, 500, true, null, func14.getTopeIdRegistro());
		Date fechaReg38 = new Date(120, 7, 16);
		reg38.setFecha(fechaReg38);
		func14.agregarRegistro(reg38);
		esp1.agregarRegistro(reg38);
		
		Registro reg39 = new Registro(func14, esp9, null, 500, true, null, func14.getTopeIdRegistro());
		Date fechaReg39 = new Date(120, 8, 6);
		reg39.setFecha(fechaReg39);
		func14.agregarRegistro(reg39);
		esp9.agregarRegistro(reg39);
		
		Registro reg40 = new Registro(func15, esp2, null, 500, true, null, func15.getTopeIdRegistro());
		Date fechaReg40 = new Date(120, 8, 1);
		reg40.setFecha(fechaReg40);
		func15.agregarRegistro(reg40);
		esp2.agregarRegistro(reg40);
		
		Registro reg41 = new Registro(func16, esp5, null, 450, true, null, func16.getTopeIdRegistro());
		Date fechaReg41 = new Date(120, 7, 16);
		reg41.setFecha(fechaReg41);
		func16.agregarRegistro(reg41);
		esp5.agregarRegistro(reg41);
		
		Registro reg42 = new Registro(func16, esp1, null, 450, true, null, func16.getTopeIdRegistro());
		Date fechaReg42= new Date(120, 7, 20);
		reg42.setFecha(fechaReg42);
		func16.agregarRegistro(reg42);
		esp1.agregarRegistro(reg42);
		
		Registro reg43 = new Registro(func16, esp7, null, 450, true, null, func16.getTopeIdRegistro());
		Date fechaReg43 = new Date(120, 7, 31);
		reg43.setFecha(fechaReg43);
		func16.agregarRegistro(reg43);
		esp7.agregarRegistro(reg43);
		
		Registro reg44 = new Registro(func17, esp6, null, 450, true, null, func17.getTopeIdRegistro());
		Date fechaReg44 = new Date(120, 7, 16);
		reg44.setFecha(fechaReg44);
		func17.agregarRegistro(reg44);
		esp6.agregarRegistro(reg44);
		
		Registro reg45 = new Registro(func17, esp7, null, 450, true, null, func17.getTopeIdRegistro());
		Date fechaReg45 = new Date(120, 7, 20);
		reg45.setFecha(fechaReg45);
		func17.agregarRegistro(reg45);
		esp7.agregarRegistro(reg45);
		
		Registro reg46 = new Registro(func17, esp2, null, 383, true, null, func17.getTopeIdRegistro());
		Date fechaReg46 = new Date(120, 8, 2);
		reg46.setFecha(fechaReg46);
		func17.agregarRegistro(reg46);
		esp2.agregarRegistro(reg46);
		
		// Nuevos registros
		
		Registro reg47 = new Registro(func19, esp9, null, 495, true, null, func19.getTopeIdRegistro());
		Date fechaReg47 = new Date(120, 10, 26);
		reg47.setFecha(fechaReg47);
		func19.agregarRegistro(reg47);
		esp9.agregarRegistro(reg47);
		
		Registro reg48 = new Registro(func19, esp2, null, 550, true, null, func19.getTopeIdRegistro());
		Date fechaReg48 = new Date(120, 10, 27);
		reg48.setFecha(fechaReg48);
		func19.agregarRegistro(reg48);
		esp2.agregarRegistro(reg48);
		
		Registro reg49 = new Registro(func19, esp8, null, 495, true, null, func19.getTopeIdRegistro());
		Date fechaReg49 = new Date(120, 10, 28);
		reg49.setFecha(fechaReg49);
		func19.agregarRegistro(reg49);
		esp8.agregarRegistro(reg49);
		
		//Premios sorteados (Nuevo)
		
		Date dp1 = new Date(120, 7, 17);
		LinkedList<String> ganadores1 = new LinkedList<String>();
		ganadores1.add(esp8.getNickname());
		func7.setFuncionSorteada();
		func7.setGanadoresSorteo(ganadores1);
		Premio p1 = new Premio(plat3, Esp3.getPremio(), Esp3, func7, dp1);
		esp8.addPremio(p1);
		
		Date dp2 = new Date(120, 8, 1);
		LinkedList<String> ganadores2 = new LinkedList<String>();
		ganadores2.add(esp1.getNickname());
		ganadores2.add(esp4.getNickname());
		func8.setFuncionSorteada();
		func8.setGanadoresSorteo(ganadores2);
		Premio p2 = new Premio(plat3, Esp3.getPremio(), Esp3, func8, dp2);
		esp1.addPremio(p2);
		esp4.addPremio(p2);
		
		Date dp3 = new Date(120, 8, 30);
		LinkedList<String> ganadores3 = new LinkedList<String>();
		ganadores3.add(esp2.getNickname());
		ganadores3.add(esp3.getNickname());
		func9.setFuncionSorteada();
		func9.setGanadoresSorteo(ganadores3);
		Premio p3 = new Premio(plat3, Esp3.getPremio(), Esp3, func9, dp3);
		esp2.addPremio(p3);
		esp3.addPremio(p3);
		
		Date dp4 = new Date(120, 8, 30);
		LinkedList<String> ganadores4 = new LinkedList<String>();
		ganadores4.add(esp5.getNickname());
		ganadores4.add(esp1.getNickname());
		ganadores4.add(esp7.getNickname());
		func16.setFuncionSorteada();
		func16.setGanadoresSorteo(ganadores4);
		Premio p4 = new Premio(plat3, Esp6.getPremio(), Esp6, func16, dp4);
		esp5.addPremio(p4);
		esp1.addPremio(p4);
		esp7.addPremio(p4);
		
		Date dp5 = new Date(120, 9, 30);
		LinkedList<String> ganadores5 = new LinkedList<String>();
		ganadores5.add(esp6.getNickname());
		ganadores5.add(esp7.getNickname());
		ganadores5.add(esp2.getNickname());
		func17.setFuncionSorteada();
		func17.setGanadoresSorteo(ganadores5);
		Premio p5 = new Premio(plat3, Esp6.getPremio(), Esp6, func17, dp5);
		esp6.addPremio(p5);
		esp7.addPremio(p5);
		esp2.addPremio(p5);
	
		//-----------------------------------------------------------------
		
		//Paquetes de Espectaculos
		
		ManejadorDePaquetes mPaquetes = ManejadorDePaquetes.getInstance();
		
		Date PfechaIni1 = new Date(120, 4, 1);
		Date PfechaFin1 = new Date(120, 6, 31);
		Date PfechaAlta1 = new Date(120, 3, 30);
		Paquete paq1 = new Paquete("Paquete de Bandas", "Paquete de bandas musicales.", PfechaIni1, PfechaFin1, 20);
		paq1.setFechaAlta(PfechaAlta1);
		mPaquetes.agregarPaquete(paq1, new ImagenDto());
		paq1.addEspectaculo(Esp1);
		paq1.addEspectaculo(Esp2);
		
		Date PfechaIni2 = new Date(120, 7, 1);
		Date PfechaFin2 = new Date(120, 8, 30);
		Date PfechaAlta2 = new Date(120, 6, 15);
		Paquete paq2 = new Paquete("Paquete Solistas", "Paquete de solistas.", PfechaIni2, PfechaFin2, 30);
		paq2.setFechaAlta(PfechaAlta2);
		mPaquetes.agregarPaquete(paq2, new ImagenDto());
		paq2.addEspectaculo(Esp3);
		paq2.addEspectaculo(Esp4);
		
		
		Date PfechaIni3 = new Date(120, 7, 15);
		Date PfechaFin3 = new Date(120, 10, 25);
		Date PfechaAlta3 = new Date(120, 7, 1);
		Paquete paq3 = new Paquete("Paquete Latino", "Paquete de espectaculos latinos.", PfechaIni3, PfechaFin3, 15);
		paq3.setFechaAlta(PfechaAlta3);
		mPaquetes.agregarPaquete(paq3, new ImagenDto());
		paq3.addEspectaculo(Esp5);
		paq3.addEspectaculo(Esp6);
		
		//Nuevo paquete
		
		Date PfechaIni4 = new Date(120, 10, 1);
		Date PfechaFin4 = new Date(120, 11, 23);
		Date PfechaAlta4 = new Date(120, 11, 25);
		Paquete paq4 = new Paquete("La Triple Dyango", "Para los rockeros romanticos.", PfechaIni4, PfechaFin4, 10);
		paq4.setFechaAlta(PfechaAlta4);
		mPaquetes.agregarPaquete(paq4, new ImagenDto());
		paq4.addEspectaculo(Esp7);
		paq4.addEspectaculo(Esp8);
		
		Esp1.addPaquete(paq1.getNombre(), paq1);
		Esp2.addPaquete(paq1.getNombre(), paq1);
		Esp3.addPaquete(paq2.getNombre(), paq2);
		Esp4.addPaquete(paq2.getNombre(), paq2);
		Esp5.addPaquete(paq3.getNombre(), paq3);
		Esp6.addPaquete(paq3.getNombre(), paq3);
		Esp7.addPaquete(paq4.getNombre(), paq4);
		Esp8.addPaquete(paq4.getNombre(), paq4);
		
		paq1.setUrlImagen("https://periodicoveraz.com/wp-content/uploads/2018/07/2-696x450.jpg");
		paq2.setUrlImagen("https://www.chismestoday.com/wp-content/uploads/2020/02/FotoJet-2020-02-08T171629.608-800x533.jpg");
		paq3.setUrlImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbQ_jrjLEAgeUu4-FcE-wKOSyyt3-c8Na_Bg&usqp=CAU");
		paq4.setUrlImagen("https://thumbs.dreamstime.com/z/guitarra-el%C3%A9ctrica-6903761.jpg");
		
		//Espectadores y paquetes
		
		esp7.agregarPaquete(paq1);
		esp7.compraPaquete(paq1.getNombre(), new Date(120, 4, 1));
		
		esp8.agregarPaquete(paq1);
		esp8.compraPaquete(paq1.getNombre(), new Date(120, 4, 20));
		
		esp1.agregarPaquete(paq2);
		esp1.compraPaquete(paq2.getNombre(), new Date(120, 7, 16));
		
		esp3.agregarPaquete(paq2);
		esp3.compraPaquete(paq2.getNombre(), new Date(120, 7, 26));
		
		esp2.agregarPaquete(paq3);
		esp2.compraPaquete(paq3.getNombre(), new Date(120, 7, 9));
		
		//Nuevas compras
		
		esp8.agregarPaquete(paq4);
		esp8.compraPaquete(paq4.getNombre(), new Date(120, 10, 25));
		
		esp9.agregarPaquete(paq4);
		esp9.compraPaquete(paq4.getNombre(), new Date(120, 10, 26));
		
		//Categorias
		
		Categoria cat1 = new Categoria("Bandas Latinas");
		Categoria cat2 = new Categoria("Solistas");
		Categoria cat3 = new Categoria("Rock en Ingles");
		Categoria cat4 = new Categoria("Musica Tropical");
		
		ManejadorDeCategorias mdc = ManejadorDeCategorias.getInstance();
		mdc.addCategoria(cat1);
		mdc.addCategoria(cat2);
		mdc.addCategoria(cat3);
		mdc.addCategoria(cat4);
		
		Esp1.addCategoria(cat3);
		Esp2.addCategoria(cat3);
		Esp3.addCategoria(cat2);
		Esp4.addCategoria(cat3);
		Esp5.addCategoria(cat1);
		Esp6.addCategoria(cat4);
		Esp7.addCategoria(cat2);
		Esp8.addCategoria(cat1);
		
		//Categorias nuevas por espectaculo
		Esp9.addCategoria(cat4);
		Esp10.addCategoria(cat4);
		

		// Favoritos (Nuevo)
		
		esp1.addEspectaculoFavorito(Esp2.getNombre());
		esp1.addEspectaculoFavorito(Esp6.getNombre());
		esp2.addEspectaculoFavorito(Esp1.getNombre());
		esp2.addEspectaculoFavorito(Esp2.getNombre());
		esp2.addEspectaculoFavorito(Esp3.getNombre());
		esp3.addEspectaculoFavorito(Esp4.getNombre());
		esp4.addEspectaculoFavorito(Esp3.getNombre());
		esp4.addEspectaculoFavorito(Esp4.getNombre());
		esp5.addEspectaculoFavorito(Esp4.getNombre());
		esp5.addEspectaculoFavorito(Esp6.getNombre());
		esp6.addEspectaculoFavorito(Esp1.getNombre());
		esp6.addEspectaculoFavorito(Esp2.getNombre());
		esp6.addEspectaculoFavorito(Esp6.getNombre());
		esp7.addEspectaculoFavorito(Esp5.getNombre());
		esp8.addEspectaculoFavorito(Esp1.getNombre());
		esp9.addEspectaculoFavorito(Esp2.getNombre());
		
		// Valoraciones (Nuevo)
		
		Valoracion v1 = new Valoracion(5, Esp1.getPlataforma(), Esp1.getNombre()); 
		esp6.addValoracion(Esp1.getPlataforma(), Esp1.getNombre(), v1);
		Esp1.addValoracion(v1);
		
		Valoracion v2 = new Valoracion(2, Esp1.getPlataforma(), Esp1.getNombre()); 
		esp7.addValoracion(Esp1.getPlataforma(), Esp1.getNombre(), v2);
		Esp1.addValoracion(v2);
		
		Valoracion v3 = new Valoracion(3, Esp1.getPlataforma(), Esp1.getNombre()); 
		esp2.addValoracion(Esp1.getPlataforma(), Esp1.getNombre(), v3);
		Esp1.addValoracion(v3);
		
		Valoracion v4 = new Valoracion(4, Esp1.getPlataforma(), Esp1.getNombre()); 
		esp8.addValoracion(Esp1.getPlataforma(), Esp1.getNombre(), v4);
		Esp1.addValoracion(v4);
		
		Valoracion v5 = new Valoracion(4, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp1.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v5);
		Esp2.addValoracion(v5);
		
		Valoracion v6 = new Valoracion(1, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp3.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v6);
		Esp2.addValoracion(v6);

		Valoracion v7 = new Valoracion(2, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp5.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v7);
		Esp2.addValoracion(v7);

		Valoracion v8 = new Valoracion(2, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp7.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v8);
		Esp2.addValoracion(v8);

		Valoracion v9 = new Valoracion(5, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp9.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v9);
		Esp2.addValoracion(v9);

		Valoracion v10 = new Valoracion(5, Esp2.getPlataforma(), Esp2.getNombre()); 
		esp6.addValoracion(Esp2.getPlataforma(), Esp2.getNombre(), v10);
		Esp2.addValoracion(v10);

		Valoracion v11 = new Valoracion(2, Esp3.getPlataforma(), Esp3.getNombre()); 
		esp1.addValoracion(Esp3.getPlataforma(), Esp3.getNombre(), v11);
		Esp3.addValoracion(v11);

		Valoracion v12 = new Valoracion(4, Esp3.getPlataforma(), Esp3.getNombre()); 
		esp4.addValoracion(Esp3.getPlataforma(), Esp3.getNombre(), v12);
		Esp3.addValoracion(v12);

		Valoracion v13 = new Valoracion(2, Esp3.getPlataforma(), Esp3.getNombre()); 
		esp6.addValoracion(Esp3.getPlataforma(), Esp3.getNombre(), v13);
		Esp3.addValoracion(v13);

		Valoracion v14 = new Valoracion(3, Esp4.getPlataforma(), Esp4.getNombre()); 
		esp5.addValoracion(Esp4.getPlataforma(), Esp4.getNombre(), v14);
		Esp4.addValoracion(v14);

		Valoracion v15 = new Valoracion(4, Esp4.getPlataforma(), Esp4.getNombre()); 
		esp4.addValoracion(Esp4.getPlataforma(), Esp4.getNombre(), v15);
		Esp4.addValoracion(v15);

		Valoracion v16 = new Valoracion(2, Esp4.getPlataforma(), Esp4.getNombre()); 
		esp1.addValoracion(Esp4.getPlataforma(), Esp4.getNombre(), v16);
		Esp4.addValoracion(v16);

		Valoracion v17 = new Valoracion(1, Esp4.getPlataforma(), Esp4.getNombre()); 
		esp2.addValoracion(Esp4.getPlataforma(), Esp4.getNombre(), v17);
		Esp4.addValoracion(v17);

		Valoracion v18 = new Valoracion(5, Esp4.getPlataforma(), Esp4.getNombre()); 
		esp3.addValoracion(Esp4.getPlataforma(), Esp4.getNombre(), v18);
		Esp4.addValoracion(v18);

		Valoracion v19 = new Valoracion(1, Esp5.getPlataforma(), Esp5.getNombre()); 
		esp4.addValoracion(Esp5.getPlataforma(), Esp5.getNombre(), v19);
		Esp5.addValoracion(v19);

		Valoracion v20 = new Valoracion(4, Esp5.getPlataforma(), Esp5.getNombre()); 
		esp9.addValoracion(Esp5.getPlataforma(), Esp5.getNombre(), v20);
		Esp5.addValoracion(v20);

		Valoracion v21 = new Valoracion(5, Esp6.getPlataforma(), Esp6.getNombre()); 
		esp6.addValoracion(Esp6.getPlataforma(), Esp6.getNombre(), v21);
		Esp6.addValoracion(v21);

		Valoracion v22 = new Valoracion(3, Esp6.getPlataforma(), Esp6.getNombre()); 
		esp7.addValoracion(Esp6.getPlataforma(), Esp6.getNombre(), v22);
		Esp6.addValoracion(v22);

		Valoracion v23 = new Valoracion(2, Esp6.getPlataforma(), Esp6.getNombre()); 
		esp2.addValoracion(Esp6.getPlataforma(), Esp6.getNombre(), v23);
		Esp6.addValoracion(v23);
		
	}
	
	
}
