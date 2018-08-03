package cn.itcast.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessPictureServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.����һ���ڴ��е�ͼƬ��BufferedImage
		int width = 120;
		int height = 30;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//2.���ñ�����ɫΪ��ɫ
		Graphics g = image.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, width, height);
		//3. ���߿�
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width-1, height-1);
		//4.����ĸ������
		String content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		int x = 10;
		int y = 20;
		Graphics2D g2d = (Graphics2D)g;//����Graphics2D�õ���תЧ��
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("����",Font.BOLD,18));
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++){
			//Ϊ��֤��������ת�Ƕ�-30~30
			int jiao = random.nextInt(60)-30;
			double theta = jiao*Math.PI/180;
			int index = random.nextInt(content.length());
			char ch = content.charAt(index);
			sb.append(ch);
			g2d.rotate(theta,x,y);//��ת�Ƕ�
			g2d.drawString(ch+"", x, y);
			g2d.rotate(-theta,x,y);//����һ�ξͻ�ԭ�Ƕ�
			x+=30;
		}
		//��session����֤�뱣������
		request.getSession().setAttribute("checkcode_session", sb.toString());
		//��������
		for(int i=1;i<=10;i++){
			g.setColor(Color.GRAY);
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//������������һ��ͼƬ��ImageIO
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
