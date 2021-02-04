package com.luna.wednesday;

import com.google.common.collect.Lists;
import com.luna.wednesday.platform.dao.ProjectDAO;
import com.luna.wednesday.platform.entity.ProjectDO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: wednesday-platform
 * @description: dao test
 * @author: luna
 * @create: 2021-02-04 14:57
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProjectDaoTest {

    @Resource
    private ProjectDAO projectDAO;


    @Test
    public void insert() {

    }
}
