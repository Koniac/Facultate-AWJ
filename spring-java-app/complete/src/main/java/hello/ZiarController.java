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
public class ZiarController{

  private List<Ziar> ziare = new ArrayList<Ziar>();

  ZiarController(){
    Ziar z1 = new Ziar(1,"Mango",2010,5,1);
    Ziar z2 = new Ziar(2,"Ananas",2006,12,1);
    Ziar z3 = new Ziar(3,"Gutuie",1990,3,1);

    ziare.add(z1);
    ziare.add(z2);
    ziare.add(z3);
  }

  @RequestMapping(value="/Ziar", method = RequestMethod.GET)
  public List<Ziar> index() {
    return this.ziare;
  }

  @RequestMapping(value="/Ziar/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id){
    for( Ziar z : this.ziare ){
      if( z.getId()==id ){
        return new ResponseEntity<Ziar>(z,new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/Ziar/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Ziar z : this.ziare) {
      if(z.getId() == id) {
        this.ziare.remove(z);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/Ziar", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="nume", defaultValue="blank") String nume, @RequestParam(value="an", defaultValue="0") int an, @RequestParam(value="luna", defaultValue="0") int luna, @RequestParam(value="zi", defaultValue="0") int zi) {
	Ziar newZiar = new Ziar(this.ziare.size() + 1,String.format(nume), an, luna, zi);
	ziare.add(newZiar);
	String numeZiar = newZiar.getNume();
	for(Ziar z : this.ziare) {
		if(z.getNume().equals(numeZiar)) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/Ziar/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="nume", defaultValue="blank") String nume, @RequestParam(value="an", defaultValue="0") int an, @RequestParam(value="luna", defaultValue="0") int luna, @RequestParam(value="zi", defaultValue="0") int zi) {
    for(Ziar z : this.ziare) {
      if(z.getId() == id) {
        z.setNume(nume);
		    z.setAn(an);
        z.setLuna(luna);
        z.setZi(zi);
		    return new ResponseEntity<Ziar>(z, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


}
