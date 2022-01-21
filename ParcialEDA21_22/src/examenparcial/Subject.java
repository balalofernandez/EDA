package examenparcial;

/**
 *
 * @author mayte
 */
public class Subject {

    private final Integer code;
    private final String name;
    private final String[] syllabus;

    public Subject(Integer code, String name, String[] syllabus) {
        this.code = code;
        this.name = name;
        this.syllabus = syllabus;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String[] getSyllabus() {
        return syllabus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.code();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subject other = (Subject) obj;
        if (this.code != other.code) {
            return false;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }
        
        int n = this.syllabus.length;
        if (n != other.syllabus.length) {
            return false;
        }
 
        for (int i = 0; i < n; i++)
        {
            if (!this.syllabus[i].equals(other.syllabus[i])) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Subject: " + "code=" + Integer.toString(code) + ", name=" + name + ", syllabus=" + Arrays.deepToString(syllabus) ;
    }    
    
    
}
