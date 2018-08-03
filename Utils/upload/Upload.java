package com.huarui.controller.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.huarui.dao.ProductDao;
import com.huarui.entity.Product;
import com.huarui.utils.NameUtil;

/**
 * 商品添加
 * @author Wangtao
 * @date   2017年7月12日
 */
@WebServlet("/manager/productAdd")
public class ProductAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProductDao productDao = new ProductDao();
    
    public ProductAddServlet() {
        super();
    }

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = null;
	    int pParentid = 0;
	    double price = 0.0;
	    int inventory = 0;
	    String detail = null;
	    String img = null;
	    //是不是文件上传表单
        if(ServletFileUpload.isMultipartContent(request)){
            //建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024*5);//设置缓存区大小1M
            //解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");//解决获取到上传的文件名乱码问题,必须写在解析文件之前
            //开始解析文件
            List<FileItem> list = null;
            try {
                list = upload.parseRequest(request);
                //遍历list
                for(FileItem fileItem : list){
                    if(fileItem.isFormField()){
                        if(Objects.equals("name", fileItem.getFieldName())){
                            name = fileItem.getString("utf-8");
                        }else if(Objects.equals("pParentid", fileItem.getFieldName())){
                            pParentid = Integer.parseInt(fileItem.getString("utf-8"));
                        }else if(Objects.equals("price", fileItem.getFieldName())){
                            price = Double.parseDouble(fileItem.getString("utf-8"));
                        }else if(Objects.equals("inventory", fileItem.getFieldName())){
                            inventory = Integer.parseInt(fileItem.getString("utf-8"));
                        }else if(Objects.equals("detail", fileItem.getFieldName())){
                            detail = fileItem.getString("utf-8");
                        }
                    }
                    else{
                        
                        String fileName = fileItem.getName();
                        //用日期作为名字
                        img = NameUtil.getName() + NameUtil.getSuffix(fileName);
                        String path = this.getServletContext().getRealPath("/images/product/")+ "/" + img;
                        String localPath = "E:/java/yimai/WebContent/images/product/" + img;
                        fileItem.write(new File(path));
                        fileItem.write(new File(localPath));
                    
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Product product = new Product();
            product.setName(name);
            product.setpParentid(pParentid);
            product.setImg(img);
            product.setPrice(price);
            product.setInventory(inventory);
            product.setDetail(detail);
  
            //保存到数据库中
            if(productDao.add(product))
                response.sendRedirect("productList");
            else {
                //保存失败
                request.setAttribute("message", "添加失败");
                request.getRequestDispatcher("product-add.jsp").forward(request, response);
            }
        }
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
