// This interface defines a contract for SQL script analysis
// Any class that implements this interface must provide
// an implementation for analysing SQL scripts
public interface AiAnalysis {

    // This method is used to analyse a given SQL script
    // Implementing classes decide how the analysis is performed
    void analyseScript(String script);

}
