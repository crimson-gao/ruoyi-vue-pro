FROM nginx
RUN rm /etc/nginx/conf.d/default.conf
#RUN rm /usr/local/nginx/conf/nginx.conf
ADD default.conf /etc/nginx/conf.d/
# ADD nginx.conf /usr/local/nginx/conf/

COPY dist/ /etc/nginx/webapp/material/