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
public class CarteController{

  private List<Carte> carti = new ArrayList<Carte>();

  CarteController(){
    Carte c1 = new Carte(1,"The Emperor's Gift","Aaron Dembski Bowden");
    Carte c2 = new Carte(2,"A study in scarlet","Sir Arthur Conan Doyle");
    Carte c3 = new Carte(3,"The last wish","Andrzej Sapkowski");

    carti.add(c1);
    carti.add(c2);
    carti.add(c3);
  }

  @RequestMapping(value="/carte", method = RequestMethod.GET)
  public List<Carte> index() {
    return this.carti;
  }

  @RequestMapping(value="/carte/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id){
    for( Carte c : this.carti ){
      if( c.getId()==id ){
        return new ResponseEntity<Carte>(c,new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/carte/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Carte c : this.carti) {
      if(c.getId() == id) {
        this.carti.remove(c);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/carte", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="titlu", defaultValue="blank") String titlu, @RequestParam(value="autor", defaultValue="blank") String autor) {
	Carte newCarte = new Carte(this.carti.size() + 1,String.format(titlu), String.format(autor));
	carti.add(newCarte);
	String numeCarte = newCarte.getTitlu();
	for(Carte c : this.carti) {
		if(c.getTitlu().equals(numeCarte)) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/carte/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="titlu", defaultValue="Updated Title") String newTitlu, @RequestParam(value="autor", defaultValue="Updated Author") String newAutor) {
    for(Carte c : this.carti) {
      if(c.getId() == id) {
        c.setTitlu(newTitlu);
		    c.setAutor(newAutor);
		return new ResponseEntity<Carte>(c, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


}
