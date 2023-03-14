/bin/bash -c "nohup /usr/sbin/sshd -D > /log.txt 2>&1 &" && sed -i -e 's/\r//g' ${1} && /bin/bash "${1}" && sleep 1s;
