package cn.eshore.mismp.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;
import com.opensymphony.xwork.util.OgnlValueStack;
import com.opensymphony.xwork.util.TextParseUtil;

public class RendTextResult implements Result {
    protected boolean parse = true;

    public static final String DEFAULT_PARAM = "text";

    private String text = "success";
    
    private String charset = "UTF-8";

    public void setParse(boolean parse) {
        this.parse = parse;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void execute(ActionInvocation invocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        OgnlValueStack stack = ActionContext.getContext().getValueStack();
        String finalText = parse ? TextParseUtil
                .translateVariables(text, stack) : text;
        if (charset != null) {
            response.setContentType("text/plain; charset="+charset);
        }
        else {
            response.setContentType("text/plain");
        }
        response.setHeader("Content-Disposition", "inline");
        PrintWriter writer = response.getWriter();
        writer.write(finalText);
        if(writer != null){
            writer.flush();
            writer.close();
        }
    }

}
