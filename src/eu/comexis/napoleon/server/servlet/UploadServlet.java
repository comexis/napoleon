package eu.comexis.napoleon.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadServlet extends HttpServlet {

  private static Logger LOG = Logger.getLogger(UploadServlet.class.getName());

  private static final long serialVersionUID = -1617966825481329203L;

  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  /**
   * Post is used when we upload a file
   */
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,
      IOException {

    Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
    List<BlobKey> blobKeys = blobs.get("file");

    if (blobKeys != null && !blobKeys.isEmpty()) {
      BlobKey key = blobKeys.get(0);
      res.setContentType("text/html");
      writeString(key.getKeyString(), res);
    } else {
      LOG.severe("Not blobkeys associated to the upload " + blobKeys);
      throw new RuntimeException("Oooops Something went wrong!!");
    }
  }

  /**
   * get is used when we try to retrieve an upload url
   */
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    String url = blobstoreService.createUploadUrl("/napoleon/upload");
    writeString(url, resp);
  }

  private void writeString(String s, HttpServletResponse res) throws IOException {
    PrintWriter out = res.getWriter();
    out.append(s).flush();

  }

}
