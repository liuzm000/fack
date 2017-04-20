<center>
						      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="box_fanye">
							<tr>
								<th height="21">
								
								总记录数：${recordCount?if_exists}
								&nbsp;
								<@pg.first export="pageUrl">
								<#assign pagenums=offset/pageSize/>
								<a href="${pageUrl?if_exists}&pageNum=1" >首页</a>
								</@pg.first>
								
								<@pg.prev export="pageUrl">
								<#assign pagenums=offset/pageSize/>
				  					<a href="${pageUrl?if_exists}&pageNum=${pagenums}" >
				  					上一页
				  					</a>
								</@pg.prev>
								<@pg.pages>
								&nbsp;
				    			<#if pageNumber == currentPageNumber>
				    				<font color="red">${pageNumber?if_exists}</font>
				    			<#else>
				    				<a href="${pageUrl?if_exists}&pageNum=${pageNumber?if_exists}" >
				    					${pageNumber?if_exists}
				    				</a>
				      			</#if>
				      				&nbsp;
								</@pg.pages>
								<@pg.next export="pageUrl">
								<#assign pagenums=offset/pageSize+2 />
								  <a href="${pageUrl?if_exists}&pageNum=${pagenums}" >下一页</a>
								</@pg.next>
								
								<#if lastPage?exists>
								&nbsp;
								<@pg.last export="pageUrl">
								<a href="${pageUrl?if_exists}&pageNum=${lastPage}" >末页</a>
								</@pg.last>
								</#if>								
								</th>
							</tr>
						</table>
</center>  