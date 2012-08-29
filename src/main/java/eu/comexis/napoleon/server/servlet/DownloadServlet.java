package eu.comexis.napoleon.server.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class DownloadServlet extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(DownloadServlet.class.getName());
  private static final long serialVersionUID = -5869130528107377854L;

  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {

    // TODO add security check : check the user have access to the parent key
    // and that the file is associated to the parent
    String key = req.getParameter("k");

    if (key == null || key.length() == 0) {
      LOG.severe("try to download a file without specifying a key");
      throw new RuntimeException("Ooops something went wrong");
    }

    BlobKey blobKey = new BlobKey(key);

    blobstoreService.serve(blobKey, resp);

  }

}
