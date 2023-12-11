package Dijkstra;

public class Vertex {
    private final String id;
  
    private final String name;
    
    public Vertex(String id, String name) {
      this.id = id;
      this.name = name;
    }
    
    public String getId() {
      return this.id;
    }
    
    public String getName() {
      return this.name;
    }
    
    public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
      return result;
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj == null)
        return false; 
      if (getClass() != obj.getClass())
        return false; 
      Vertex other = (Vertex)obj;
      if (this.id == null) {
        if (other.id != null)
          return false; 
      } else if (!this.id.equals(other.id)) {
        return false;
      } 
      return true;
    }
    
    public String toString() {
      return this.name;
    }
    
}
