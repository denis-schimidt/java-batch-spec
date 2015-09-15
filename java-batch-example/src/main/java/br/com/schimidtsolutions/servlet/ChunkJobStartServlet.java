package br.com.schimidtsolutions.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.batch.runtime.JobExecution;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.schimidtsolutions.annotations.Chunk;
import br.com.schimidtsolutions.service.AbstractBatchExecutionService;

@WebServlet(name = "ChunkJobStartServlet", urlPatterns = "/run_batch_chunk_job")
public class ChunkJobStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1725853366864605316L;

	@Inject @Chunk
	private AbstractBatchExecutionService batchExecutor;

	enum OperatorAction {
		START, RESTART, VIEW;
	}

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
 
        OperatorAction action = OperatorAction.valueOf(req.getParameter("action"));
	
		long executionId = -1;
		 
	    if (req.getParameter("executionId") != null) {
	        executionId = Long.valueOf(req.getParameter("executionId"));
	    }
	
	    switch (action) {
	     case START:
	        executionId = batchExecutor.submitJob();
	        this.write(String.format("Id do java batch que está %d rodando.", executionId), res);
	        break;
	        
	     case RESTART:
	        executionId = batchExecutor.restartJob(executionId);
	        this.write(String.format("Id do java batch que está %d que restartou.", executionId), res);
	        break;
	        
	     case VIEW:
	        JobExecution execution = 
	                batchExecutor.getJobExecutionDetails(executionId);
	        this.write("Execution Id \n " + execution, res);
	        break;
	    }
	    
        this.write("<h2>selecione uma opção abaixo:</h2>", res);
 
        this.writeLink(OperatorAction.VIEW,
                "Veja detalhes da execução Id." 
                        + executionId, executionId, req, res);
 
        this.writeLink(OperatorAction.RESTART,
                "Restart o Job." + executionId, executionId, req, res);
 
        this.writeLink(OperatorAction.START,
                "Inicie um novo Job.", null, req, res);
	}
	
	private void write(String message, HttpServletResponse res) 
            throws IOException {
        
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println(message);
    }
 
    private void writeLink(OperatorAction action, String text, Long executionId,
                           HttpServletRequest req, HttpServletResponse res)
            throws IOException {
 
        PrintWriter out = res.getWriter();
        StringBuilder sb = new StringBuilder();
        
        sb.append("<a href=\"")
          .append(req.getContextPath())
          .append( "/run_batch_chunk_job" )
          .append("?action=")
          .append(action.name());
 
        if (executionId != null) {
            sb.append("&executionId=").append(executionId);
        }
 
        sb.append("\">").append(text).append("</a>");
 
        out.println(sb.toString());
        out.println("<hr/>");
    }
}
