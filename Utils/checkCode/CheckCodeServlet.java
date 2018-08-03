package com.huarui.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.创建一个内存中的图片，BufferedImage
		int width = 120;
		int height = 30;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//2.设置背景颜色为黄色
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//3. 画边框
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width-1, height-1);
		//4.成语
		Random random = new Random();
		
		String content = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		int x = 10;
		int y = 20;
		Graphics2D g2d = (Graphics2D)g;//利用Graphics2D得到旋转效果
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("微软雅黑",Font.BOLD,20));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++){
			char ch = content.charAt(random.nextInt(content.length()));
			sb.append(ch);
			g2d.drawString(ch+"", x, y);
			x+=30;
		}
		//用session将验证码保存起来
		request.getSession().setAttribute("checkCode", sb.toString());
		//在浏览器输出这一张图片，ImageIO
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
