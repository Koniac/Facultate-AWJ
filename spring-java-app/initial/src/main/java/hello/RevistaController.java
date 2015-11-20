package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;

@RestController
public class RevistaController{

  private List<Revista> reviste = new ArrayList<Revista>();

  RevistaController(){
    Revista r1 = new Revista(1,"Mango",2010,5);
    Revista r2 = new Revista(2,"Ananas",2006,12);
    Revista r3 = new Revista(3,"Gutuie",1990,3);

    reviste.add(r1);
    reviste.add(r2);
    reviste.add(r3);
  }

  @RequestMapping(value="/revista", method = RequestMethod.GET)
  public List<Revista> index() {
    return this.reviste;
  }

  @RequestMapping(value="/revista/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id){
    for( Revista r : this.reviste ){
      if( r.getId()==id ){
        return new ResponseEntity<Revista>(r,new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/revista/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Revista r : this.reviste) {
      if(r.getId() == id) {
        this.reviste.remove(r);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/revista", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="nume", defaultValue="blank") String nume, @RequestParam(value="an", defaultValue="0") int an, @RequestParam(value="luna", defaultValue="0") int luna) {
	Revista newRevista = new Revista(this.reviste.size() + 1,String.format(nume), an, luna);
	reviste.add(newRevista);
	String numeRevista = newRevista.getNume();
	for(Revista r : this.reviste) {
		if(r.getNume().equals(numeRevista)) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/revista/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="nume", defaultValue="blank") String nume, @RequestParam(value="an", defaultValue="0") int an, @RequestParam(value="luna", defaultValue="0") int luna) {
    for(Revista r : this.reviste) {
      if(r.getId() == id) {
        r.setNume(nume);
		    r.setAn(an);
        r.setLuna(luna);
		    return new ResponseEntity<Revista>(r, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


}
