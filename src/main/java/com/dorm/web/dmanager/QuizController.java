package com.dorm.web.dmanager;
import com.dorm.cms.quiz.entity.Quiz;
import com.dorm.cms.quiz.service.imp.QuizService;

import com.dorm.cms.quizResult.entity.QuizResult;
import com.dorm.cms.quizResult.service.imp.QuizResultService;
import com.dorm.cms.student.entity.Student;
import com.dorm.cms.student.service.imp.StudentService;
import com.dorm.role.admin.entity.Admin;
import com.dorm.role.admin.service.imp.AdminService;
import com.dorm.utils.Tools;
import com.dorm.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dmanager")
public class QuizController extends BaseController {

    @Autowired
    QuizService quizService;
    @Autowired
    QuizResultService quizResultService;
    @Autowired
    AdminService userService;
    @Autowired
    StudentService studentService;

    @RequestMapping("/doQuiz")
    public ModelAndView doQuiz(HttpServletRequest request){

        ModelAndView mv = this.getModeAndView();
        try {
            List<Quiz> quizList = quizService.findList();
            Quiz quiz = quizList.get(0);
            mv.addObject("quiz", quiz);

            //获取登录用户
            Admin user = (Admin)request.getSession().getAttribute("admin");
            mv.addObject("userName", user.getUserName());

            int end = 0;
            try{
                end = studentService.findById(user.getId()).getChara(); //有性格类型，表示已做过测试
            }catch (Exception e){

            }

            mv.addObject("end",end);//没结束测试
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("quiz/doQuiz");
        return mv;
    }

    @RequestMapping("/quizSave")
    public ModelAndView quizSave(String userName, int num, int result){
        ModelAndView mv = this.getModeAndView();
        try {
            Admin admin = userService.findByUserName(userName);
            mv.addObject("userName", userName);
            QuizResult quizResult = new QuizResult();
            quizResult.setId(Tools.getUUID());
            quizResult.setQuizNum(num);
            quizResult.setUserId(admin.getId());
            quizResult.setUserName(userName);
            quizResult.setResult(result);
            quizResultService.save(quizResult);
            num++;
            if(num <=108){
                Quiz quiz = quizService.findByNum(num);
                mv.addObject("quiz", quiz);
                mv.addObject("end",0);//没结束测试

            }else{
                resultDate(userName,mv);
                mv.setViewName("quiz/quizResultData");
                return mv;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("quiz/doQuiz");

        return mv;
    }

    @RequestMapping("/quizResultDate")
    public ModelAndView quizResultDate(HttpServletRequest request){
        ModelAndView mv=this.getModeAndView();
        //获取登录用户
        Admin user = (Admin)request.getSession().getAttribute("admin");
        String userName = user.getUserName();
        resultDate(userName,mv);
        mv.setViewName("quiz/quizResultData");
        return mv;

    }
    //性格分析
    public void resultDate(String userName, ModelAndView mv){
        try {
            int state1count = (int) quizResultService.state1count(userName);
            int state2count = (int) quizResultService.state2count(userName);
            int state3count = (int) quizResultService.state3count(userName);
            int state4count = (int) quizResultService.state4count(userName);
            int state5count = (int) quizResultService.state5count(userName);
            int state6count = (int) quizResultService.state6count(userName);
            int state7count = (int) quizResultService.state7count(userName);
            int state8count = (int) quizResultService.state8count(userName);
            int state9count = (int) quizResultService.state9count(userName);

            System.out.println("username------"+userName);
            Admin user = userService.findByUserName(userName);
            System.out.println("user------"+user.getId());
            Student student = studentService.findById(user.getId());
            student.setS1(state1count);
            student.setS2(state2count);
            student.setS3(state3count);
            student.setS4(state4count);
            student.setS5(state5count);
            student.setS6(state6count);
            student.setS7(state7count);
            student.setS8(state8count);
            student.setS9(state9count);
            //感情中心2+3+4
            int c1 = state2count + state3count + state4count;
            //思想中心5+6+7
            int c2 = state5count + state6count + state7count;
            //本能中心8+9+1
            int c3 = state8count + state9count + state1count;
            //判断性格
            int chara;
            if(c1>c2){
                if(c1>c3) {
                    chara = 1;
                }else{
                    chara = 3;
                }
            }else{
                if(c2>c3){
                    chara = 2;
                }else{
                    chara = 3;
                }

            }
            //保存性格
            student.setChara(chara);
            studentService.saveChara(student);

            System.out.println(userName+"1型："+state1count);
            System.out.println(userName+"2型："+state2count);
            System.out.println(userName+"3型："+state3count);
            System.out.println(userName+"4型："+state4count);
            System.out.println(userName+"5型："+state5count);
            System.out.println(userName+"6型："+state6count);
            System.out.println(userName+"7型："+state7count);
            System.out.println(userName+"8型："+state8count);
            System.out.println(userName+"9型："+state9count);
            mv.addObject("state1", state1count);
            mv.addObject("state2", state2count);
            mv.addObject("state3", state3count);
            mv.addObject("state4", state4count);
            mv.addObject("state5", state5count);
            mv.addObject("state6", state6count);
            mv.addObject("state7", state7count);
            mv.addObject("state8", state8count);
            mv.addObject("state9", state9count);

            //判断性格
            String[] resultMessage = new String[9];
            /**resultMessage[0] = "完美主义者：完美者、改进型、捍卫原则型、秩序大使；";
            resultMessage[1] = "助人者：成就他人者、助人型、博爱型、爱心大使；";
            resultMessage[2] = "成就者：成就者、实践型、实干型；";
            resultMessage[3] = "艺术型：浪漫者、艺术型、自我型；";
            resultMessage[4] = "智慧型：观察者、思考型、理智型；";
            resultMessage[5] = "忠诚型：寻求安全者、谨慎型、忠诚型；";
            resultMessage[6] = "快乐主义型：创造可能者、活跃型、享乐型；";
            resultMessage[7] = "领袖型：挑战者、权威型、领袖；";
            resultMessage[8] = "和平型：维持和谐者、和谐型、平淡型。";**/
            resultMessage[0] = "[Instinctive Center]Perfectionist: perfectionist, improver, defender of principle, ambassador of order";
            resultMessage[1] = "[Emotional Center]People who help others: achievers, people who help others, people who love others, ambassadors of love";
            resultMessage[2] = "[Emotional Center]Achievers: achievers, practitioners, doers";
            resultMessage[3] = "[Emotional Center]Artistic type: romantic, artistic, ego type";
            resultMessage[4] = "[Ideas Center]Intellectual: observer, thinker, rational";
            resultMessage[5] = "[Ideas Center]Loyal: safety seekers, cautious, loyal";
            resultMessage[6] = "[Ideas Center]Hedonism: creative, active, hedonistic";
            resultMessage[7] = "[Instinctive Center]Leaders: challengers, authoritative, leaders";
            resultMessage[8] = "[Instinctive Center]Peace type: maintain harmony, harmony, plain type";
            int[] resultNum = new int[9];
            resultNum[0] = state1count;
            resultNum[1] = state2count;
            resultNum[2] = state3count;
            resultNum[3] = state4count;
            resultNum[4] = state5count;
            resultNum[5] = state6count;
            resultNum[6] = state7count;
            resultNum[7] = state8count;
            resultNum[8] = state9count;

            int maxNum = resultNum[0];
            String maxResultMessage = resultMessage[0];
            for(int i=1;i<=8;i++){
                if(maxNum<resultNum[i]){
                    maxNum = resultNum[i];
                    maxResultMessage = resultMessage[i];
                }
            }
            System.out.println(maxResultMessage);
            mv.addObject("subtitle_text",maxResultMessage);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
